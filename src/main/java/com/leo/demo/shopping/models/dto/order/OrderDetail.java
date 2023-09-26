package com.leo.demo.shopping.models.dto.order;

import com.leo.demo.shopping.models.entities.OrderMeal;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@Data
public class OrderDetail {
    private Integer id;
    private String orderNumber;
    private String contactMobile;
    private BigDecimal totalAmount;
    private String orderStatus;
    private String userName;
    private String address;
    private String email;
    private String transactionNo;
    private LocalDateTime payTime;
    private List<OrderMeal> mealItemList;
}
