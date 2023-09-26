package com.leo.demo.shopping.service;


import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.order.CreateOrderRequest;
import com.leo.demo.shopping.models.dto.order.OrderDetail;
import com.leo.demo.shopping.models.dto.order.PaymentCallbackRequest;
import com.leo.demo.shopping.models.entities.Order;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
public interface IOrderService {
    List<Order> getOrderList(String mobileNumber);


    OrderDetail createOrder(CreateOrderRequest request);

    OrderDetail orderDetail(Integer orderId);

    List<OrderDetail> orderList(BaseRequest request);

    boolean cancel(Integer orderId);

    boolean paymentCallback(PaymentCallbackRequest request);
}
