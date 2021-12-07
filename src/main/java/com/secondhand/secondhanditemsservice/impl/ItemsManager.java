package com.secondhand.secondhanditemsservice.impl;

import io.swagger.model.GetItemsByUserIdsResponse;
import io.swagger.model.Item;
import io.swagger.model.ItemResource;
import io.swagger.model.SoldInfo;
import org.springframework.stereotype.Service;
import org.threeten.bp.Instant;
import org.threeten.bp.OffsetDateTime;
import org.threeten.bp.ZoneOffset;

import javax.sql.DataSource;
import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public List<Item> getItemsForUserIds(List<String> userIds) throws SQLException{
        List<Item> itemList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            StringBuilder sqlBuilder = new StringBuilder("SELECT * from items i left join ItemSoldInfo isi on i.id = isi.itemId ");

            if (userIds != null && !userIds.isEmpty()) {
                sqlBuilder.append(" where userId in (");
                sqlBuilder.append("'").append(userIds.get(0)).append("'");

                for (int i = 1; i < userIds.size(); i++) {
                    sqlBuilder.append(", ");
                    sqlBuilder.append("'").append(userIds.get(i)).append("'");
                }
                sqlBuilder.append(")");
            }

            ResultSet resultSet = statement.executeQuery(sqlBuilder.toString());
            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getLong("id"));
                item.setName(resultSet.getString("name"));
                item.setAddedOn(OffsetDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getDate("addedOn").getTime()), ZoneOffset.UTC));
                item.setCategory(resultSet.getString("category"));
                item.setUserId(resultSet.getString("userId"));

                if (resultSet.getBoolean("isSold")) {
                    SoldInfo soldInfo = new SoldInfo();
                    soldInfo.setSoldOn(OffsetDateTime.ofInstant(Instant.ofEpochMilli(resultSet.getDate("soldOn").getTime()), ZoneOffset.UTC));
                    soldInfo.setSoldAtPrice(resultSet.getFloat("soldAtPrice"));
                    soldInfo.setSoldToUserId(resultSet.getString("soldToUserId"));
                    item.setSoldInfo(soldInfo);
                } else {
                    item.setSoldInfo(null);
                }
                itemList.add(item);
            }

            updateResourcesForItems(itemList, connection);
        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
        return itemList;
    }

    private void updateResourcesForItems(List<Item> itemList, Connection connection) {
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

    public void addItem(Item item) {
        try (Connection connection = dataSource.getConnection()) {

        } catch (SQLException se) {
            System.out.println("Error: " + se.getMessage());
        }
    }
}
