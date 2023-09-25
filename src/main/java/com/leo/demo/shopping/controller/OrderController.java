package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.dto.order.CreateOrderRequest;
import com.leo.demo.shopping.models.dto.order.OrderDetail;
import com.leo.demo.shopping.service.ICartService;
import com.leo.demo.shopping.service.IOrderService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    private IOrderService orderService;

    public OrderController(ICartService cartService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/createOrder", method = {RequestMethod.POST})
    public OrderDetail createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(request);
    }
}
