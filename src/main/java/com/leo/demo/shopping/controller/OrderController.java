package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.annotation.NeedLogin;
import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.order.CreateOrderRequest;
import com.leo.demo.shopping.models.dto.order.OrderDetail;
import com.leo.demo.shopping.models.dto.order.PaymentCallbackRequest;
import com.leo.demo.shopping.service.IOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private IOrderService orderService;

    public OrderController(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST})
    @NeedLogin
    public OrderDetail createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }

    @RequestMapping(value = "/orderDetail/{orderId}", method = {RequestMethod.GET})
    public OrderDetail orderDetail(@PathVariable Integer orderId) {
        return orderService.orderDetail(orderId);
    }

    @RequestMapping(value = "/orderList", method = {RequestMethod.POST})
    @NeedLogin
    public List<OrderDetail> orderList(@RequestBody BaseRequest request) {
        return orderService.orderList(request);
    }

    @RequestMapping(value = "/cancel/{orderId}", method = {RequestMethod.GET})
    public boolean cancel(@PathVariable Integer orderId) {
        return orderService.cancel(orderId);
    }

    @RequestMapping(value = "/paymentCallback", method = {RequestMethod.POST})
    public boolean paymentCallback(@RequestBody PaymentCallbackRequest request) {
        return orderService.paymentCallback(request);
    }
}
