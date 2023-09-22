package com.leo.demo.shopping.models.dto.food;

import com.alibaba.fastjson.JSON;

/**
 * @author leo
 * @date 2023/1/17
 */
public class FoodFacilityPageRequest extends PageableRequest {
    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
