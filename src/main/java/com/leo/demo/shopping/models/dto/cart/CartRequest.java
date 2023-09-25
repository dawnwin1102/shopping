package com.leo.demo.shopping.models.dto.cart;

import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class CartRequest extends BaseRequest {
    @ApiModelProperty(value = "cart ID")
    @NotBlank
    private String cartId;
    @ApiModelProperty(value = "meal list")
    private List<CartMeal> mealItemList;
}
