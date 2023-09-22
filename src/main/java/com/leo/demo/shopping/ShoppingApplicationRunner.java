package com.leo.demo.shopping;

import com.leo.demo.shopping.models.entities.FoodFacility;
import com.leo.demo.shopping.service.IFoodFacilityService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ShoppingApplicationRunner implements ApplicationRunner {
    @Autowired
    private IFoodFacilityService foodFacilityService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String fileName = "";
        if (args.getOptionValues("filename") != null) {
            fileName = args.getOptionValues("filename").get(0);
        }
        if (Strings.isNotBlank(fileName)) {
            log.info("start load data from file:", fileName);
            foodFacilityService.initFoodFacilityDB(fileName);
        } else {
            Long count = foodFacilityService.getFoodFacilityCount();
            if (count == 0) {
                log.info("start initialize data");
                List<FoodFacility> result = foodFacilityService.initFoodFacilityDB("");
                log.info("{} record has initialize into db.", result.size());
            }
        }
    }
}
