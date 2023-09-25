package com.leo.demo.shopping.models.dto.order;

import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class CreateOrderRequest extends BaseRequest {
    @ApiModelProperty(value = "Contact Mobile")
    @NotBlank
    private String contactMobile;
    @ApiModelProperty(value = "cart id")
    @NotBlank
    private String cartId;
}
