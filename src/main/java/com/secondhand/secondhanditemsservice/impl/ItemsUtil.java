package com.secondhand.secondhanditemsservice.impl;

import io.swagger.model.ItemResource;

public class ItemsUtil {
    public static ItemResource.ResourceTypeEnum getResourceTypeFromString(String resourceType) {
        if(resourceType.equalsIgnoreCase("IMAGE")) {
            return ItemResource.ResourceTypeEnum.IMAGE;
        } else {
            return ItemResource.ResourceTypeEnum.VIDEO;
        }
    }
}
