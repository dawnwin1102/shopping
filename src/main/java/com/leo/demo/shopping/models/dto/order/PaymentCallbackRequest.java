package com.leo.demo.shopping.models.dto.order;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class PaymentCallbackRequest {
    @ApiModelProperty(value = "order id")
    @NotBlank
    private Integer orderId;
    @ApiModelProperty(value = "order number")
    @NotBlank
    private String orderNumber;
    @ApiModelProperty(value = "cardNo")
    @NotBlank
    private String cardNo = "";
    @ApiModelProperty(value = "CVC")
    @NotBlank
    private String CVC = "";
    @ApiModelProperty(value = "pay status")
    @NotBlank
    private String payStatus = "";
}
