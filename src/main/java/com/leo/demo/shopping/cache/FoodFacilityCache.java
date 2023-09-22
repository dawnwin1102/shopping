package com.leo.demo.shopping.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.leo.demo.shopping.dao.FoodFacilityRepository;
import com.leo.demo.shopping.models.dto.food.FoodFacilityRequest;
import com.leo.demo.shopping.models.entities.FoodFacility;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Component
public class FoodFacilityCache {
    private static final String FoodFacilityKey = "food_facility";
    private Cache<String, Object> cache;
    private final FoodFacilityRepository foodFacilityRepository;

    public FoodFacilityCache(FoodFacilityRepository foodFacilityRepository) {
        cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)
                .expireAfterWrite(300, TimeUnit.SECONDS)
                .build();

        this.foodFacilityRepository = foodFacilityRepository;
    }

    public List<FoodFacility> getAllFoodFacilityByApplicant(FoodFacilityRequest request) {
        String cacheKey = getCacheKey(request.getApplicant());
        List<FoodFacility> list = (List<FoodFacility>) cache.getIfPresent(cacheKey);
        if (list == null || list.isEmpty()) {
            // here we also can get result from redis ,if no result then go to db
            list = foodFacilityRepository.findAllByApplicant(request.getApplicant());
            cache.put(cacheKey, list);
        }

        return list;
    }

    private String getCacheKey(String applicant) {
        return String.format(FoodFacilityKey + "_" + applicant);
    }
}
