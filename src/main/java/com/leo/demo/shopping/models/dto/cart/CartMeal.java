package com.leo.demo.shopping.models.dto.cart;

import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class CartMeal extends BaseRequest {
    @ApiModelProperty(value = "cart ID")
    private Integer mealId;
    @ApiModelProperty(value = "meal name")
    private String mealName;
    @ApiModelProperty(value = "unit Price")
    private BigDecimal unitPrice;
    @ApiModelProperty(value = "meal quantity")
    @Min(1)
    private Integer quantity;
    @ApiModelProperty(value = "description")
    private String description = "";
    @ApiModelProperty(value = "imgUrl")
    private String imgUrl = "";
}
