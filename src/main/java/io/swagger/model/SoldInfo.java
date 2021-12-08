package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.threeten.bp.OffsetDateTime;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * SoldInfo
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-08T06:06:07.363Z")


public class SoldInfo   {
  @JsonProperty("itemId")
  private Long itemId = null;

  @JsonProperty("soldAtPrice")
  private Float soldAtPrice = null;

  @JsonProperty("soldOn")
  private OffsetDateTime soldOn = null;

  @JsonProperty("soldToUserId")
  private String soldToUserId = null;

  public SoldInfo itemId(Long itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Get itemId
   * @return itemId
  **/
  @ApiModelProperty(value = "")


  public Long getItemId() {
    return itemId;
  }

  public void setItemId(Long itemId) {
    this.itemId = itemId;
  }

  public SoldInfo soldAtPrice(Float soldAtPrice) {
    this.soldAtPrice = soldAtPrice;
    return this;
  }

  /**
   * Get soldAtPrice
   * @return soldAtPrice
  **/
  @ApiModelProperty(value = "")


  public Float getSoldAtPrice() {
    return soldAtPrice;
  }

  public void setSoldAtPrice(Float soldAtPrice) {
    this.soldAtPrice = soldAtPrice;
  }

  public SoldInfo soldOn(OffsetDateTime soldOn) {
    this.soldOn = soldOn;
    return this;
  }

  /**
   * Get soldOn
   * @return soldOn
  **/
  @ApiModelProperty(value = "")

  @Valid

  public OffsetDateTime getSoldOn() {
    return soldOn;
  }

  public void setSoldOn(OffsetDateTime soldOn) {
    this.soldOn = soldOn;
  }

  public SoldInfo soldToUserId(String soldToUserId) {
    this.soldToUserId = soldToUserId;
    return this;
  }

  /**
   * Get soldToUserId
   * @return soldToUserId
  **/
  @ApiModelProperty(value = "")


  public String getSoldToUserId() {
    return soldToUserId;
  }

  public void setSoldToUserId(String soldToUserId) {
    this.soldToUserId = soldToUserId;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SoldInfo soldInfo = (SoldInfo) o;
    return Objects.equals(this.itemId, soldInfo.itemId) &&
        Objects.equals(this.soldAtPrice, soldInfo.soldAtPrice) &&
        Objects.equals(this.soldOn, soldInfo.soldOn) &&
        Objects.equals(this.soldToUserId, soldInfo.soldToUserId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, soldAtPrice, soldOn, soldToUserId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SoldInfo {\n");
    
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    soldAtPrice: ").append(toIndentedString(soldAtPrice)).append("\n");
    sb.append("    soldOn: ").append(toIndentedString(soldOn)).append("\n");
    sb.append("    soldToUserId: ").append(toIndentedString(soldToUserId)).append("\n");
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

