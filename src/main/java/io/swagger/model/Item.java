package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.ItemResource;
import io.swagger.model.SoldInfo;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Item
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-01T02:18:22.352Z")


public class Item   {
  @JsonProperty("id")
  private Long id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("description")
  private String description = null;

  /**
   * Item category
   */
  public enum CategoryEnum {
    FURNITURE("Furniture"),
    
    HOUSEHOLD("Household"),
    
    BOOKS_AND_SUPPLIES("Books and Supplies"),
    
    CARS("Cars"),
    
    ELECTRONICS("Electronics");

    private String value;

    CategoryEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static CategoryEnum fromValue(String text) {
      for (CategoryEnum b : CategoryEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("category")
  private CategoryEnum category = null;

  @JsonProperty("addedOn")
  private OffsetDateTime addedOn = null;

  @JsonProperty("price")
  private Float price = null;

  @JsonProperty("soldInfo")
  private SoldInfo soldInfo = null;

  @JsonProperty("userId")
  private String userId = null;

  @JsonProperty("resources")
  private ItemResource resources = null;

  public Item id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Item name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Item description(String description) {
    this.description = description;
    return this;
  }

  /**
   * Get description
   * @return description
  **/
  @ApiModelProperty(value = "")


  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Item category(CategoryEnum category) {
    this.category = category;
    return this;
  }

  /**
   * Item category
   * @return category
  **/
  @ApiModelProperty(value = "Item category")


  public CategoryEnum getCategory() {
    return category;
  }

  public void setCategory(CategoryEnum category) {
    this.category = category;
  }

  public Item addedOn(OffsetDateTime addedOn) {
    this.addedOn = addedOn;
    return this;
  }

  /**
   * Get addedOn
   * @return addedOn
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getAddedOn() {
    return addedOn;
  }

  public void setAddedOn(OffsetDateTime addedOn) {
    this.addedOn = addedOn;
  }

  public Item price(Float price) {
    this.price = price;
    return this;
  }

  /**
   * Get price
   * @return price
  **/
  @ApiModelProperty(value = "")


  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public Item soldInfo(SoldInfo soldInfo) {
    this.soldInfo = soldInfo;
    return this;
  }

  /**
   * Get soldInfo
   * @return soldInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public SoldInfo getSoldInfo() {
    return soldInfo;
  }

  public void setSoldInfo(SoldInfo soldInfo) {
    this.soldInfo = soldInfo;
  }

  public Item userId(String userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
  **/
  @ApiModelProperty(value = "")


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public Item resources(ItemResource resources) {
    this.resources = resources;
    return this;
  }

  /**
   * Get resources
   * @return resources
  **/
  @ApiModelProperty(value = "")

  @Valid

  public ItemResource getResources() {
    return resources;
  }

  public void setResources(ItemResource resources) {
    this.resources = resources;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.name, item.name) &&
        Objects.equals(this.description, item.description) &&
        Objects.equals(this.category, item.category) &&
        Objects.equals(this.addedOn, item.addedOn) &&
        Objects.equals(this.price, item.price) &&
        Objects.equals(this.soldInfo, item.soldInfo) &&
        Objects.equals(this.userId, item.userId) &&
        Objects.equals(this.resources, item.resources);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description, category, addedOn, price, soldInfo, userId, resources);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    description: ").append(toIndentedString(description)).append("\n");
    sb.append("    category: ").append(toIndentedString(category)).append("\n");
    sb.append("    addedOn: ").append(toIndentedString(addedOn)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    soldInfo: ").append(toIndentedString(soldInfo)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    resources: ").append(toIndentedString(resources)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

