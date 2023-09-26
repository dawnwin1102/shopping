package com.leo.demo.shopping.service;


import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import com.opencsv.exceptions.CsvException;
import reactor.util.function.Tuple4;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author leo
 * @date 2023/9/24
 */
public interface IMealItemService {
    List<MealItem> initShoppingDB(String fileName) throws IOException, CsvException;

    MealItem getMealItemDetail(Integer id);

    Long getMealItemCount();

    Map<Integer, CartMeal> checkMealItem(CartRequest request);

    Tuple4<Boolean, Map<Integer, CartMeal>, List<MealItem>, BigDecimal> checkOrderMealItem(List<CartMeal> mealList);

    List<MealItem> getMealList();
}
