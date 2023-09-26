package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import com.leo.demo.shopping.service.ICartService;
import com.leo.demo.shopping.service.IMealItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author leo
 * @date 2023/9/24
 */
@RestController
@RequestMapping("/meal")
public class MealController {
    private IMealItemService mealItemService;

    public MealController(IMealItemService mealItemService) {
        this.mealItemService = mealItemService;
    }

    @RequestMapping(value = "/getMealList", method = {RequestMethod.GET})
    public List<MealItem> getMealList() {
        return mealItemService.getMealList();
    }

    @RequestMapping(value = "/mealDetail/{mealId}", method = {RequestMethod.GET})
    public MealItem mealDetail(@PathVariable Integer mealId) {
        return mealItemService.getMealItemDetail(mealId);
    }
}
