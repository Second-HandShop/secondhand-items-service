package com.secondhand.secondhanditemsservice.impl;

import io.swagger.model.Item;
import io.swagger.model.ItemResource;
import io.swagger.model.SoldInfo;
import org.springframework.stereotype.Service;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ItemsManager {

    private DataSource dataSource = null;

    public ItemsManager(DataSource dataSource)  {
        this.dataSource = dataSource;
    }

    public List<Item> getItemsForUserIds(List<String> userIds, String nameFilter) throws SQLException{
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            StringBuilder sqlBuilder = new StringBuilder("SELECT * from items i left join ItemSoldInfo isi on i.id = isi.itemId ");

            boolean nameFilterAdded = false;
            if (userIds != null && !userIds.isEmpty()) {
                sqlBuilder.append(" where userId in (");
                sqlBuilder.append("'").append(userIds.get(0)).append("'");

                for (int i = 1; i < userIds.size(); i++) {
                    sqlBuilder.append(", ");
                    sqlBuilder.append("'").append(userIds.get(i)).append("'");
                }
                sqlBuilder.append(")");
                if(nameFilter != null) {
                    sqlBuilder.append(" and i.name like *").append(nameFilter).append("*");
                    nameFilterAdded = true;
                }
            }
            if(!nameFilterAdded && nameFilter!= null) {
                sqlBuilder.append(" where i.name like '%").append(nameFilter).append("%'");
            }

            ResultSet resultSet = statement.executeQuery(sqlBuilder.toString());
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setAddedOn(OffsetDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getTimestamp("addedOn").getTime()), ZoneOffset.UTC));
                item.setCategory(resultSet.getString("category"));
                item.setUserId(resultSet.getString("userId"));
                item.setPrice(resultSet.getFloat("price"));
                item.setDescription(resultSet.getString("description"));

                if (resultSet.getBoolean("isSold")) {
                    SoldInfo soldInfo = new SoldInfo();
                    soldInfo.setSoldOn(OffsetDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getTimestamp("soldOn").getTime()), ZoneOffset.UTC));
                    soldInfo.setSoldAtPrice(resultSet.getFloat("soldAtPrice"));
                    soldInfo.setSoldToUserId(resultSet.getString("soldToUserId"));
                    item.setSoldInfo(soldInfo);
                } else {
                    item.setSoldInfo(null);
                }
                itemList.add(item);
            }

            getResourcesForItems(itemList, connection);
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
        return itemList;
    }

    private void getResourcesForItems(List<Item> itemList, Connection connection) {
        try {
            Statement statement = connection.createStatement();

            StringBuilder sqlBuilder = new StringBuilder("SELECT * from ItemResources where itemId in(");

            if (itemList != null && !itemList.isEmpty()) {
                sqlBuilder.append(itemList.get(0).getId());

                for (int i = 1; i < itemList.size(); i++) {
                    sqlBuilder.append(", ");
                    sqlBuilder.append(itemList.get(i).getId());
                }
                sqlBuilder.append(")");
            }

            Map<Long, List<ItemResource>> itemIdToResourcesMap = new HashMap<>();

            ResultSet resultSet = statement.executeQuery(sqlBuilder.toString());
            while (resultSet.next()) {
                ItemResource itemResource = new ItemResource();
                itemResource.setName(resultSet.getString("name"));
                itemResource.setResourceType(ItemsUtil.getResourceTypeFromString(resultSet.getString("type")));
                itemResource.setResourceLink(resultSet.getString("resourceLink"));

                Long itemId = resultSet.getLong("itemId");
                if(itemIdToResourcesMap.get(itemId) == null) {
                    List<ItemResource> resources = new ArrayList<>();
                    resources.add(itemResource);
                    itemIdToResourcesMap.put(itemId, resources);
                } else {
                    itemIdToResourcesMap.get(itemId).add(itemResource);
                }
            }

            itemList.forEach(item -> {
                item.setResources(itemIdToResourcesMap.get(item.getId()));
            });
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
    }

    public int addItem(Item item) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO `items` (`id`, `name`, `description`, `category`, `addedOn`, `price`, `userId`, `isSold`) VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)";

            int index = 1;
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(index++, item.getName());
            statement.setString(index++, item.getDescription());
            statement.setString(index++, item.getCategory());
            statement.setTimestamp(index++, new Timestamp(item.getAddedOn().toInstant().toEpochMilli()));
            statement.setFloat(index++, item.getPrice());
            statement.setString(index++, item.getUserId());
            statement.setBoolean(index, false);

            int itemAdded =  statement.executeUpdate();

            //get the item id of the inserted row
            ResultSet resultSet = statement.getGeneratedKeys();

            long addedItemId = 0;
            if(resultSet.next()) {
                addedItemId = resultSet.getLong(1);
            } else {
                throw new SQLException("There was a problem adding Resources");
            }

            int resourceAdded  = 1;

            if(item.getResources() != null && item.getResources().size() > 0) {
                resourceAdded = addResource(addedItemId, item.getResources(), connection);
            }

            if(resourceAdded > 0 && itemAdded > 0) {
                connection.commit();
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
        return 0;
    }

    private int deleteResource(long itemId, Connection connection) throws SQLException {
        String sql = "DELETE from `ItemResources` where `itemId` = ?;";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, itemId);
        return statement.executeUpdate();
    }

    private int addResource(long itemId, List<ItemResource> itemResources, Connection connection) throws SQLException {
        String sql = "INSERT INTO `ItemResources` (`name`, `type`, `resourceLink`, `itemId`) VALUES (?, ?, ?, ?);";

        PreparedStatement statement = connection.prepareStatement(sql);
        int index = 1;

        for (ItemResource itemResource : itemResources) {
            statement.setString(index++, itemResource.getName());
            statement.setString(index++, itemResource.getResourceType().toString());
            statement.setString(index++, itemResource.getResourceLink());
            statement.setLong(index, itemId);
            statement.addBatch();
        }

        return statement.executeBatch().length;
    }

    public int updateItem(Item item){
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            String sql = "UPDATE `items` set `name` = ?, `description` = ?, `category` = ?, `addedOn` = ? , `price` = ?, `userId` = ?, `isSold` = ? where `id` = ?";

            int index = 1;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(index++, item.getName());
            statement.setString(index++, item.getDescription());
            statement.setString(index++, item.getCategory());
            statement.setTimestamp(index++, new Timestamp(item.getAddedOn().toInstant().toEpochMilli()));
            statement.setFloat(index++, item.getPrice());
            statement.setString(index++, item.getUserId());
            statement.setBoolean(index++, false);
            statement.setLong(index, item.getId());

            int itemUpdated =  statement.executeUpdate();

            int resourceAdded = 1;

            deleteResource(item.getId(), connection);

            if(item.getResources() != null && item.getResources().size() > 0) {
                resourceAdded = addResource(item.getId(), item.getResources(), connection);
            }
            if(resourceAdded > 0 && itemUpdated > 0) {
                connection.commit();
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
        return 0;
    }

    public int addSoldInfo(SoldInfo soldInfo) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            //update the item with isSold column true
            String sqlItem = "UPDATE `items` SET `isSold`= TRUE WHERE id = " + soldInfo.getItemId();
            Statement itemStatement = connection.createStatement();
            int itemUpdated = itemStatement.executeUpdate(sqlItem);

            //insert item
            String sqlSoldInfo = "INSERT INTO `ItemSoldInfo`(`itemId`, `soldToUserId`, `soldOn`, `soldAtPrice`) VALUES ( ?, ?, ?, ?) ";

            int index = 1;
            PreparedStatement statement = connection.prepareStatement(sqlSoldInfo);
            statement.setLong(index++, soldInfo.getItemId());
            statement.setString(index++, soldInfo.getSoldToUserId());
            statement.setTimestamp(index++,  new Timestamp(soldInfo.getSoldOn().toInstant().toEpochMilli()));
            statement.setFloat(index, soldInfo.getSoldAtPrice());

            int soldInfoAdded = statement.executeUpdate();

            if(soldInfoAdded > 0 && itemUpdated > 0) {
                connection.commit();
                return 1;
            } else {
                return 0;
            }
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
        return 0;
    }
}
