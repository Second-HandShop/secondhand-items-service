/**
 * NOTE: This class is auto generated by the swagger code generator program (2.4.24).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.GetItemsByUserIdsResponse;
import io.swagger.model.Item;
import io.swagger.model.SoldInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-10T02:01:00.731Z")

@Validated
@Api(value = "item", description = "the item API")
@RequestMapping(value = "")
public interface ItemApi {

    @ApiOperation(value = "Add an Item", nickname = "addItem", notes = "Add an item for the user.", tags={ "item", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/item",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addItem(@ApiParam(value = "Item object that needs to be added." ,required=true )  @Valid @RequestBody Item body);


    @ApiOperation(value = "Add an ItemSoldInfo", nickname = "addItemSoldInfo", notes = "Add an itemSoldInfo for an Item.", tags={ "item", })
    @ApiResponses(value = { 
        @ApiResponse(code = 405, message = "Invalid input") })
    @RequestMapping(value = "/item/sold",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.POST)
    ResponseEntity<Void> addItemSoldInfo(@ApiParam(value = "Item Sold Info object that needs to be added." ,required=true )  @Valid @RequestBody SoldInfo body);


    @ApiOperation(value = "Finds items bought by the user", nickname = "getBoughtItemsByUserIds", notes = "Multiple UserId values can be provided with comma separated strings", response = GetItemsByUserIdsResponse.class, tags={ "item", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetItemsByUserIdsResponse.class),
        @ApiResponse(code = 400, message = "Invalid UserIds value") })
    @RequestMapping(value = "/item/bought",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetItemsByUserIdsResponse> getBoughtItemsByUserIds(@ApiParam(value = "Items bought by given userIds will be retrieved") @Valid @RequestParam(value = "userIds", required = false) List<String> userIds);


    @ApiOperation(value = "Finds items", nickname = "getItemsByUserIds", notes = "Multiple UserId values can be provided with comma separated strings", response = GetItemsByUserIdsResponse.class, tags={ "item", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "successful operation", response = GetItemsByUserIdsResponse.class),
        @ApiResponse(code = 400, message = "Invalid UserIds value") })
    @RequestMapping(value = "/item",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<GetItemsByUserIdsResponse> getItemsByUserIds(@ApiParam(value = "Items posted by given userIds will be retrieved") @Valid @RequestParam(value = "userIds", required = false) List<String> userIds,@ApiParam(value = "Items with given categories will be retrieved") @Valid @RequestParam(value = "categories", required = false) List<String> categories,@ApiParam(value = "Item name for search") @Valid @RequestParam(value = "name", required = false) String name);


    @ApiOperation(value = "Update an existing Item", nickname = "updateItem", notes = "", tags={ "item", })
    @ApiResponses(value = { 
        @ApiResponse(code = 400, message = "Invalid ID supplied"),
        @ApiResponse(code = 404, message = "Item not found"),
        @ApiResponse(code = 405, message = "Validation exception") })
    @RequestMapping(value = "/item",
        produces = { "application/json" }, 
        consumes = { "application/json" },
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateItem(@ApiParam(value = "Item object that needs to be updated." ,required=true )  @Valid @RequestBody Item body);

}
