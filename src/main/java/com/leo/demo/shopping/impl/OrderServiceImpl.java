package com.leo.demo.shopping.impl;

import cn.hutool.core.date.DateUtil;
import com.leo.demo.shopping.dao.MealItemRepository;
import com.leo.demo.shopping.dao.OrderMealRepository;
import com.leo.demo.shopping.dao.OrderRepository;
import com.leo.demo.shopping.exception.BusinessException;
import com.leo.demo.shopping.models.base.ResponseCodeEnum;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.order.CreateOrderRequest;
import com.leo.demo.shopping.models.dto.order.OrderDetail;
import com.leo.demo.shopping.models.dto.order.PaymentCallbackRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import com.leo.demo.shopping.models.entities.Order;
import com.leo.demo.shopping.models.entities.OrderMeal;
import com.leo.demo.shopping.models.enums.OrderStatusEnum;
import com.leo.demo.shopping.service.ICartService;
import com.leo.demo.shopping.service.IMealItemService;
import com.leo.demo.shopping.service.IOrderService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuples;

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

    private static final String ORDER_LOCK = "ORDER_LOCK";

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
    public synchronized OrderDetail createOrder(CreateOrderRequest request) {
        if (request.getMealList() == null || request.getMealList().isEmpty()) {
            throw new BusinessException(ResponseCodeEnum.Code_2001);
        }
        var checkResult = mealItemService.checkOrderMealItem(request.getMealList());
        if (!checkResult.getT1()) {
            throw new BusinessException(ResponseCodeEnum.Code_2002);
        }
        List<CartMeal> checkedMealList = checkResult.getT2().values().stream().collect(Collectors.toList());
        var savedOrderResult = saveOrder(request, checkResult, checkedMealList);
        return getOrderDetail(savedOrderResult.getT1(), savedOrderResult.getT2());
    }

    @Override
    public OrderDetail orderDetail(Integer orderId) {
        var order = orderRepository.findById(orderId);
        if (!order.isPresent()) {
            throw new BusinessException(ResponseCodeEnum.Code_2003);
        }
        var orderMealList = orderMealRepository.findByOrderIdEquals(orderId);
        return getOrderDetail(order.get(), orderMealList);
    }

    @Override
    public List<OrderDetail> orderList(String mobile) {
        var orderList = orderRepository.findByContactMobile(mobile);
        List<OrderDetail> orderDetailList = new ArrayList<>();
        orderList.forEach(i -> {
            orderDetailList.add(getOrderDetail(i, null));
        });
        return orderDetailList;
    }

    @Override
    public synchronized boolean cancel(Integer orderId) {
        var order = orderRepository.findById(orderId);
        //  cancel order here:orderStatus
        if (order.get().getOrderStatus().equals(OrderStatusEnum.WaitPay.getStatus())) {
            order.get().setOrderStatus(OrderStatusEnum.Cancel.getStatus());
            order.get().setUpdateTime(LocalDateTime.now());
        }

        return true;
    }

    @Override
    public synchronized boolean paymentCallback(PaymentCallbackRequest request) {
        var order = orderRepository.findById(request.getOrderId());
        //  set payinfo to order:transactionNo,payTime,orderStatus,cardNo,CVC
        if (request.getPayStatus().equals("success")) {
            if (order.get().getOrderStatus().equals(OrderStatusEnum.WaitPay.getStatus())) {
                order.get().setOrderStatus(OrderStatusEnum.PaySuccess.getStatus());
                order.get().setPayTime(LocalDateTime.now());
                order.get().setCardNo(request.getCardNo());
                order.get().setCVC(request.getCVC());
                order.get().setUpdateTime(LocalDateTime.now());
            }
        } else if (request.getPayStatus().equals("expired")) {
            if (order.get().getOrderStatus().equals(OrderStatusEnum.WaitPay.getStatus())) {
                order.get().setOrderStatus(OrderStatusEnum.Cancel.getStatus());
                order.get().setUpdateTime(LocalDateTime.now());
            }
        }

        return true;
    }

    @NotNull
    private OrderDetail getOrderDetail(Order order, List<OrderMeal> checkedMealList) {
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setId(order.getId());
        orderDetail.setOrderNumber(order.getOrderNumber());
        orderDetail.setOrderStatus(order.getOrderStatus());
        orderDetail.setContactMobile(order.getContactMobile());
        orderDetail.setPayTime(order.getPayTime());
        orderDetail.setMealItemList(checkedMealList);
        orderDetail.setTransactionNo(order.getTransactionNo());
        orderDetail.setAddress(order.getAddress());
        orderDetail.setEmail(order.getEmail());
        orderDetail.setUserName(order.getUserName());
        orderDetail.setTotalAmount(order.getTotalAmount());
        return orderDetail;
    }

    @Transactional
    private Tuple2<Order, List<OrderMeal>> saveOrder(CreateOrderRequest request, Tuple4<Boolean, Map<Integer, CartMeal>, List<MealItem>, BigDecimal> checkResult, List<CartMeal> checkedMealList) {
        Order newOrder = new Order();
        newOrder.setTotalAmount(checkResult.getT4());
        newOrder.setOrderNumber(generateOrderNumber());
        newOrder.setOrderStatus(OrderStatusEnum.WaitPay.getStatus());
        newOrder.setAddress(request.getAddress());
        newOrder.setContactMobile(request.getContactMobile());
        newOrder.setEmail(request.getEmail());
        newOrder.setUserName(request.getUserName());
        //set default expire time for 15 minutes
        newOrder.setExpiryDate(LocalDateTime.now().plusMinutes(15));
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
        return Tuples.of(newOrder, orderMealList);
    }

    private String generateOrderNumber() {
        // just for sample , here need redis to generate self-increasing number
        return String.format("T-%s", DateUtil.format(LocalDateTime.now(), "yyyyMMddHHmmss"));
    }
}
