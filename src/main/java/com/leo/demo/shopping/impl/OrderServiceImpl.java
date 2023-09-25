package com.leo.demo.shopping.impl;

import cn.hutool.core.date.DateUtil;
import com.leo.demo.shopping.cache.CartCache;
import com.leo.demo.shopping.dao.MealItemRepository;
import com.leo.demo.shopping.dao.OrderMealRepository;
import com.leo.demo.shopping.dao.OrderRepository;
import com.leo.demo.shopping.exception.BusinessException;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.dto.order.CreateOrderRequest;
import com.leo.demo.shopping.models.dto.order.OrderDetail;
import com.leo.demo.shopping.models.entities.Order;
import com.leo.demo.shopping.models.entities.OrderMeal;
import com.leo.demo.shopping.service.ICartService;
import com.leo.demo.shopping.service.IMealItemService;
import com.leo.demo.shopping.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author leo
 * @date 2023/9/25
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    ICartService cartService;
    @Autowired
    private IMealItemService mealItemService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderMealRepository orderMealRepository;
    @Autowired
    MealItemRepository mealItemRepository;

    @Override
    public List<Order> getOrderList(String mobileNumber) {
        return null;
    }

    @Override
    @Transactional
    public OrderDetail createOrder(CreateOrderRequest request) {
        var cartMealItemList = cartService.getAllCartMealList(request.getCartId());
        if (cartMealItemList == null || cartMealItemList.isEmpty()) {
            throw new BusinessException("2001", "Item list is empty");
        }
        CartRequest cartRequest = new CartRequest();
        cartRequest.setCartId(request.getCartId());
        cartRequest.setMealItemList(cartMealItemList);
        var checkResult = mealItemService.checkOrderMealItem(cartRequest);
        if (!checkResult.getT1()) {
            throw new BusinessException("2002", "Item check failed");
        }
        List<CartMeal> checkedMealList = checkResult.getT2().values().stream().collect(Collectors.toList());
        Order newOrder = new Order();
        newOrder.setTotalAmount(checkResult.getT4());
        newOrder.setOrderNumber(generateOrderNumber());
        newOrder.setOrderStatus("waitPay");
        newOrder.setAddress("");
        newOrder.setContactMobile(request.getContactMobile());
        newOrder.setEmail("");
        newOrder.setUserName("");
        Order savedOrder = orderRepository.save(newOrder);
        List<OrderMeal> orderMealList = new ArrayList<>();
        checkedMealList.forEach(item -> {
            OrderMeal orderMeal = new OrderMeal();
            orderMeal.setOrderId(savedOrder.getId());
            orderMeal.setMealId(item.getMealId());
            orderMeal.setQuantity(item.getQuantity());
            orderMeal.setUnitPrice(item.getUnitPrice());
            orderMeal.setTotalAmount(item.getUnitPrice().multiply(new BigDecimal(item.getQuantity())));
            orderMealList.add(orderMeal);
        });
        orderMealRepository.saveAll(orderMealList);
        mealItemRepository.saveAllAndFlush(checkResult.getT3());
        return new OrderDetail();
    }

    private String generateOrderNumber() {
        // just for sample , here need redis to generate self-increasing number
        return String.format("T-%s", DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
    }
}
