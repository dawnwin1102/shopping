package com.leo.demo.shopping.models.dto.food;

import com.leo.demo.shopping.models.base.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author leo
 * @date 2023/1/17
 */
@Data
public class FoodFacilityRequest extends BaseRequest {
    @ApiModelProperty(value = "applicant name")
    private String applicant;
    @ApiModelProperty(value = "address")
    private String address;
}
