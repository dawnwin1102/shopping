package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.dto.home.HomeRequest;
import com.leo.demo.shopping.models.dto.home.HomeResponse;
import com.leo.demo.shopping.models.entities.MealItem;
import com.leo.demo.shopping.service.ICartService;
import com.leo.demo.shopping.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
    public List<CartMeal> addToCart(@RequestBody CartRequest request) {
        return cartService.addToCart(request);
    }

    @RequestMapping(value = "/getCartMealList/{cartId}", method = {RequestMethod.GET})
    public List<CartMeal> getCartMealList(@PathVariable String cartId) {
        return cartService.getAllCartMealList(cartId);
    }
}
