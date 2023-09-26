package com.leo.demo.shopping.service;


import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
public interface ICartService {
    List<CartMeal> addToCart(CartRequest request);

    boolean clearCart(String mobile);

    public List<CartMeal> getCartMealList(String mobile);
}
