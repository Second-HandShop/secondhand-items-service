package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ItemResource
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-10T02:01:00.731Z")


public class ItemResource   {
  @JsonProperty("name")
  private String name = null;

  /**
   * Gets or Sets resourceType
   */
  public enum ResourceTypeEnum {
    IMAGE("IMAGE"),
    
    VIDEO("VIDEO");

    private String value;

    ResourceTypeEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ResourceTypeEnum fromValue(String text) {
      for (ResourceTypeEnum b : ResourceTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("resourceType")
  private ResourceTypeEnum resourceType = null;

  @JsonProperty("resourceLink")
  private String resourceLink = null;

  public ItemResource name(String name) {
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

  public ItemResource resourceType(ResourceTypeEnum resourceType) {
    this.resourceType = resourceType;
    return this;
  }

  /**
   * Get resourceType
   * @return resourceType
  **/
  @ApiModelProperty(value = "")


  public ResourceTypeEnum getResourceType() {
    return resourceType;
  }

  public void setResourceType(ResourceTypeEnum resourceType) {
    this.resourceType = resourceType;
  }

  public ItemResource resourceLink(String resourceLink) {
    this.resourceLink = resourceLink;
    return this;
  }

  /**
   * Get resourceLink
   * @return resourceLink
  **/
  @ApiModelProperty(value = "")


  public String getResourceLink() {
    return resourceLink;
  }

  public void setResourceLink(String resourceLink) {
    this.resourceLink = resourceLink;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemResource itemResource = (ItemResource) o;
    return Objects.equals(this.name, itemResource.name) &&
        Objects.equals(this.resourceType, itemResource.resourceType) &&
        Objects.equals(this.resourceLink, itemResource.resourceLink);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, resourceType, resourceLink);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemResource {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    resourceType: ").append(toIndentedString(resourceType)).append("\n");
    sb.append("    resourceLink: ").append(toIndentedString(resourceLink)).append("\n");
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

