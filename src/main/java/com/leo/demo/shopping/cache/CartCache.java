package com.leo.demo.shopping.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.leo.demo.shopping.dao.FoodFacilityRepository;
import com.leo.demo.shopping.dao.MealItemRepository;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.dto.food.FoodFacilityRequest;
import com.leo.demo.shopping.models.entities.FoodFacility;
import com.leo.demo.shopping.service.IMealItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Component
public class CartCache {
    private static final String CARTKEY = "cart_";
    private Cache<String, List<CartMeal>> cache;
    @Autowired
    private IMealItemService mealItemService;

    public CartCache() {
        cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .build();
    }

    // use synchronized for thread safe ,in a distribute enviroment need use distribute lock like redis
    public synchronized List<CartMeal> addCartMeal(CartRequest request) {
        String cacheKey = getCacheKey(request.getCartId());
        Map<Integer, CartMeal> validItemMap = mealItemService.checkMealItem(request);
        List<CartMeal> requstCartMealList = validItemMap.values().stream().collect(Collectors.toList());
        List<CartMeal> cartList = cache.getIfPresent(cacheKey);
        if (cartList == null || cartList.isEmpty()) {
            cartList = new ArrayList<>();
            cartList.addAll(request.getMealItemList());
        } else {
            for (CartMeal cartMeal : requstCartMealList) {
                var findCart = cartList.stream().filter(item -> item.getMealId().equals(cartMeal.getMealId())).findAny();
                if (findCart.isPresent()) {
                    findCart.get().setQuantity(findCart.get().getQuantity() + cartMeal.getQuantity());
                } else {
                    cartList.add(cartMeal);
                }
            }
        }
        cache.put(cacheKey, cartList);
        return cartList;
    }

    public List<CartMeal> getAllCartMealList(String cartId) {
        String cacheKey = getCacheKey(cartId);
        List<CartMeal> list = (List<CartMeal>) cache.getIfPresent(cacheKey);
        if (list == null || list.isEmpty()) {
            return new ArrayList<>();
        }

        return list;
    }

    private String getCacheKey(String cartId) {
        return String.format(CARTKEY + cartId);
    }
}
