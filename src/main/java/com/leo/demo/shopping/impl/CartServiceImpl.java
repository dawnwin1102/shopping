package com.leo.demo.shopping.impl;

import com.leo.demo.shopping.cache.CartCache;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author leo
 * @date 2023/9/25
 */
@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    CartCache cartCache;

    @Override
    public List<CartMeal> addToCart(CartRequest request) {
        return cartCache.addCartMeal(request);
    }

    @Override
    public List<CartMeal> getAllCartMealList(String cartId) {
        return cartCache.getAllCartMealList(cartId);
    }

    @Override
    public boolean clearCart(CartRequest request) {
        return false;
    }
}
