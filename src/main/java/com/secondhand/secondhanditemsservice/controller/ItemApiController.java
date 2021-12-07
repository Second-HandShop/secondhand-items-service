package com.secondhand.secondhanditemsservice.controller;

import com.secondhand.secondhanditemsservice.impl.ItemsManager;
import io.swagger.api.ItemApi;
import io.swagger.model.GetItemsByUserIdsResponse;
import io.swagger.model.Item;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-07T05:55:40.646Z")

@Controller
public class ItemApiController implements ItemApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @Autowired
    ItemsManager itemsManager;

    @org.springframework.beans.factory.annotation.Autowired
    public ItemApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Void> addItem(@ApiParam(value = "Item object that needs to be added." ,required=true )  @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<GetItemsByUserIdsResponse> getItemsByUserIds(@ApiParam(value = "Items posted by given userIds will be retrieved") @Valid @RequestParam(value = "userIds", required = false) List<String> userIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                List<Item> itemList = itemsManager.getItemsForUserIds(userIds);
                Map<String, List<Item>> userIdsToIdsMap = new HashMap<>();

                itemList.forEach( item -> {
                    if(userIdsToIdsMap.get(item.getUserId()) == null) {
                        List<Item> items = new ArrayList<>();
                        items.add(item);
                        userIdsToIdsMap.put(item.getUserId(), items);
                    } else {
                        userIdsToIdsMap.get(item.getUserId()).add(item);
                    }
                });

                GetItemsByUserIdsResponse getItemsByUserIdsResponse = new GetItemsByUserIdsResponse();
                getItemsByUserIdsResponse.setUserIdsToIdsMap(userIdsToIdsMap);
                return new ResponseEntity<GetItemsByUserIdsResponse>(getItemsByUserIdsResponse, HttpStatus.OK);
            } catch (Exception e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<GetItemsByUserIdsResponse>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<GetItemsByUserIdsResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

    public ResponseEntity<Void> updateItem(@ApiParam(value = "Item object that needs to be updated." ,required=true )  @Valid @RequestBody Item body) {
        String accept = request.getHeader("Accept");
        return new ResponseEntity<Void>(HttpStatus.NOT_IMPLEMENTED);
    }

}
