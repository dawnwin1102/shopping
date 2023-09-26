package com.leo.demo.shopping.models.dto.cart;

import com.leo.demo.shopping.models.base.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class CartRequest extends BaseRequest {
    @ApiModelProperty(value = "mobile")
    @NotBlank
    private String mobile;
    @ApiModelProperty(value = "meal list")
    private List<CartMeal> mealItemList;
}
