package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.controller.base.BaseApiController;
import com.leo.demo.shopping.models.base.BaseResponse;
import com.leo.demo.shopping.models.dto.food.FoodFacilityPageRequest;
import com.leo.demo.shopping.models.dto.food.FoodFacilityRequest;
import com.leo.demo.shopping.models.entities.FoodFacility;
import com.leo.demo.shopping.service.IFoodFacilityService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author leo
 * @date 2023/1/17
 */
@RestController
@RequestMapping("/food")
public class FoodFacilityController extends BaseApiController {
    private IFoodFacilityService foodFacilityService;

    public FoodFacilityController(IFoodFacilityService foodFacilityService) {
        this.foodFacilityService = foodFacilityService;
    }

    @ApiOperation(value = "get food facility list", httpMethod = "POST")
    @RequestMapping(value = "/list", method = {RequestMethod.POST})
    @Cacheable("food_facility")
    public  Page<FoodFacility> list(@RequestBody @Validated FoodFacilityPageRequest request) {
        return foodFacilityService.getFoodFacilitylist(request);
//        return this.execService(request, foodFacilityService::getFoodFacilitylist);
    }


//    @ApiOperation(value = "get food facility list need authentication", httpMethod = "POST")
//    @RequestMapping(value = "/auth/list", method = {RequestMethod.POST})
//    @RequiresAuthentication
//    @Cacheable("auth_food_facility")
//    public BaseResponse authlist(@RequestBody @Validated FoodFacilityPageRequest request) {
//        return this.execService(request, foodFacilityService::getFoodFacilitylist);
//    }
//
//    @ApiOperation(value = "get food facility list by applicant", httpMethod = "POST")
//    @RequestMapping(value = "/listByApplicant", method = {RequestMethod.POST})
//    public BaseResponse listByApplicant(@RequestBody FoodFacilityRequest request) {
//        return this.execService(request, foodFacilityService::getFoodFacilityByApplicant);
//    }
//
//    @ApiOperation(value = "get food facility list by applicant or address", httpMethod = "POST")
//    @RequestMapping(value = "/listByApplicantOrAddress", method = {RequestMethod.POST})
//    @Cacheable("food_facility")
//    public BaseResponse listByApplicantOrAddress(@RequestBody FoodFacilityRequest request) {
//        return this.execService(request, foodFacilityService::getFoodFacilityByApplicantOrAddress);
//    }
//
//    @ApiOperation(value = "get food facility detail", httpMethod = "GET")
//    @RequestMapping(value = "/detail/{id}", method = {RequestMethod.GET})
//    @Cacheable("food_facility")
//    public BaseResponse getFoodFacilityDetail(@PathVariable @NotNull Integer id) {
//        return this.execService(id, foodFacilityService::getFoodFacilityDetail);
//    }
}
