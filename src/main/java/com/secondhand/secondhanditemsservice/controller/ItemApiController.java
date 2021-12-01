package com.secondhand.secondhanditemsservice.controller;

import io.swagger.api.ItemApi;
import io.swagger.model.GetItemsByUserIdsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2021-12-01T02:18:22.352Z")

@Controller
public class ItemApiController implements ItemApi {

    private static final Logger log = LoggerFactory.getLogger(ItemApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public ItemApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<List<GetItemsByUserIdsResponse>> getItemsByUserIds(@NotNull @ApiParam(value = "Items posted by given userIds will be retrieved", required = true) @Valid @RequestParam(value = "userIds", required = true) List<String> userIds) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/xml")) {
            try {
                return new ResponseEntity<List<GetItemsByUserIdsResponse>>(objectMapper.readValue("<null>  <userIdsToIdsMap></userIdsToIdsMap></null>", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/xml", e);
                return new ResponseEntity<List<GetItemsByUserIdsResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        if (accept != null && accept.contains("application/json")) {
            try {
                return new ResponseEntity<List<GetItemsByUserIdsResponse>>(objectMapper.readValue("[]", List.class), HttpStatus.NOT_IMPLEMENTED);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<List<GetItemsByUserIdsResponse>>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<List<GetItemsByUserIdsResponse>>(HttpStatus.NOT_IMPLEMENTED);
    }

}