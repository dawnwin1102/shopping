package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.annotation.NeedLogin;
import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.service.ICartService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@RestController
@RequestMapping("/cart")
public class CartController {
    private ICartService cartService;

    public CartController(ICartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/addToCart", method = {RequestMethod.POST})
    @NeedLogin
    public List<CartMeal> addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    @RequestMapping(value = "/getCartMealList", method = {RequestMethod.POST})
    @NeedLogin
    public List<CartMeal> getCartMealList(@RequestBody @Nullable BaseRequest request) {
        return cartService.getCartMealList(request);
    }

    @RequestMapping(value = "/clearCart/{mobile}", method = {RequestMethod.GET})
    public boolean clearCart(@PathVariable String mobile) {
        return cartService.clearCart(mobile);
    }
}
