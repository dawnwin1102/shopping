package com.leo.demo.shopping.service;


import cn.hutool.core.lang.Pair;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.dto.food.FoodFacilityPageRequest;
import com.leo.demo.shopping.models.dto.food.FoodFacilityRequest;
import com.leo.demo.shopping.models.entities.FoodFacility;
import com.leo.demo.shopping.models.entities.MealItem;
import com.opencsv.exceptions.CsvException;
import org.springframework.data.domain.Page;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author leo
 * @date 2023/9/24
 */
public interface IMealItemService {
    List<MealItem> initShoppingDB(String fileName) throws IOException, CsvException;

    Page<MealItem> getMealItemlist(FoodFacilityPageRequest request);

    MealItem getMealItemDetail(Integer id);

    Long getMealItemCount();

    Map<Integer, CartMeal> checkMealItem(CartRequest request);

    Tuple4<Boolean, Map<Integer, CartMeal>, List<MealItem>, BigDecimal> checkOrderMealItem(CartRequest request);
}
