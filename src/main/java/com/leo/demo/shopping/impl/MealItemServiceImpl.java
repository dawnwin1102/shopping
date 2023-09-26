package com.leo.demo.shopping.impl;

import com.leo.demo.shopping.dao.MealItemRepository;
import com.leo.demo.shopping.exception.BusinessException;
import com.leo.demo.shopping.models.dto.cart.CartMeal;
import com.leo.demo.shopping.models.dto.cart.CartRequest;
import com.leo.demo.shopping.models.entities.MealItem;
import com.leo.demo.shopping.service.IMealItemService;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.exceptions.CsvException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuples;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MealItemServiceImpl implements IMealItemService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MealItemRepository mealItemRepository;
    @Autowired
    private EntityManager entityManager;

    @Override
    public List<MealItem> initShoppingDB(String fileName) throws IOException, CsvException {
        if (StringUtils.isBlank(fileName) || Files.notExists(Paths.get(fileName))) {
            // set to default file
            fileName = "datafile/MealItem.csv";
        }
        ClassPathResource CPR = new ClassPathResource(fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(CPR.getInputStream());
        List<MealItem> beans = new CsvToBeanBuilder(inputStreamReader)
                .withType(MealItem.class).build().parse();
        return mealItemRepository.saveAllAndFlush(beans);
    }

    @Override
    public MealItem getMealItemDetail(Integer id) {
        MealItem res = mealItemRepository.findById(id).orElse(new MealItem());
        return res;
    }

    @Override
    public Long getMealItemCount() {
        return mealItemRepository.count();
    }

    @Override
    public Map<Integer, CartMeal> checkMealItem(CartRequest request) {
        Map<Integer, CartMeal> map = getMealItemMap(request.getMealItemList());
        List<Integer> mealIdList = request.getMealItemList().stream().map(CartMeal::getMealId).collect(Collectors.toList());
        var dbMealItemList = mealItemRepository.findAllById(mealIdList);
        for (var entry : map.entrySet()) {
            var exitInDb = dbMealItemList.stream().filter(i -> i.getId().equals(entry.getValue().getMealId())).findFirst();
            if (!exitInDb.isPresent() || exitInDb.get().getInventory() < entry.getValue().getQuantity()) {
                //remove not available item
                log.warn("meal item:{} is not available", entry.getKey());
                map.remove(entry.getKey());
            }
        }

        return map;
    }

    @NotNull
    private Map<Integer, CartMeal> getMealItemMap(List<CartMeal> mealList) {
        Map<Integer, CartMeal> map = new HashMap<>();
        mealList.forEach(meal -> {
            var exitMeal = map.get(meal.getMealId());
            if (exitMeal == null) {
                map.put(meal.getMealId(), meal);
            } else {
                exitMeal.setQuantity(exitMeal.getQuantity() + meal.getQuantity());
            }
        });
        return map;
    }

    /**
     *
     * @param mealList
     * @return checkResult, request orderMeal map,dbMealItemList
     */
    @Override
    public Tuple4<Boolean, Map<Integer, CartMeal>, List<MealItem>, BigDecimal> checkOrderMealItem(List<CartMeal> mealList) {
        Map<Integer, CartMeal> map = getMealItemMap(mealList);
        List<Integer> mealIdList = mealList.stream().map(CartMeal::getMealId).collect(Collectors.toList());
        BigDecimal totalAmount = BigDecimal.ZERO;
        var dbMealItemList = mealItemRepository.findAllById(mealIdList);
        for (var meal : map.values()) {
            var exitInDb = dbMealItemList.stream().filter(i -> i.getId().equals(meal.getMealId())).findFirst();
            if (!exitInDb.isPresent() || exitInDb.get().getInventory() < meal.getQuantity()) {
                log.warn("meal item:{} is not available", meal.getMealId());
                return Tuples.of(false, map, dbMealItemList, totalAmount);
            }
            Integer remainCount = exitInDb.get().getInventory() - meal.getQuantity();
            if (remainCount < 0) {
                log.warn("no enough meal item:{}", meal.getMealId());
                return Tuples.of(false, map, dbMealItemList, totalAmount);
            }
            exitInDb.get().setInventory(remainCount);
            // set latest price from db
            meal.setUnitPrice(exitInDb.get().getUnitPrice());
            totalAmount.add(meal.getUnitPrice().multiply(new BigDecimal(meal.getQuantity())));
        }

        return Tuples.of(true, map, dbMealItemList, totalAmount);
    }

    @Override
    public List<MealItem> getMealList() {
        return mealItemRepository.findAll();
    }
}
