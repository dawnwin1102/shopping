package com.leo.demo.shopping.models.dto.order;

import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class CreateOrderRequest extends BaseRequest {
    private String email;
    private String address;
    private String userName;
    @ApiModelProperty(value = "meal List")
    @NotEmpty
    List<CartMeal> mealList;
}
