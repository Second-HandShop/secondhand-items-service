package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.Item;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * GetItemsByUserIdsResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-08T19:27:18.275Z")


public class GetItemsByUserIdsResponse   {
  @JsonProperty("userIdsToIdsMap")
  @Valid
  private Map<String, List<Item>> userIdsToIdsMap = null;

  public GetItemsByUserIdsResponse userIdsToIdsMap(Map<String, List<Item>> userIdsToIdsMap) {
    this.userIdsToIdsMap = userIdsToIdsMap;
    return this;
  }

  public GetItemsByUserIdsResponse putUserIdsToIdsMapItem(String key, List<Item> userIdsToIdsMapItem) {
    if (this.userIdsToIdsMap == null) {
      this.userIdsToIdsMap = new HashMap<String, List<Item>>();
    }
    this.userIdsToIdsMap.put(key, userIdsToIdsMapItem);
    return this;
  }

  /**
   * Get userIdsToIdsMap
   * @return userIdsToIdsMap
  **/
  @ApiModelProperty(value = "")

  @Valid

  public Map<String, List<Item>> getUserIdsToIdsMap() {
    return userIdsToIdsMap;
  }

  public void setUserIdsToIdsMap(Map<String, List<Item>> userIdsToIdsMap) {
    this.userIdsToIdsMap = userIdsToIdsMap;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GetItemsByUserIdsResponse getItemsByUserIdsResponse = (GetItemsByUserIdsResponse) o;
    return Objects.equals(this.userIdsToIdsMap, getItemsByUserIdsResponse.userIdsToIdsMap);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userIdsToIdsMap);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class GetItemsByUserIdsResponse {\n");
    
    sb.append("    userIdsToIdsMap: ").append(toIndentedString(userIdsToIdsMap)).append("\n");
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

