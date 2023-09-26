package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.models.dto.home.HomeRequest;
import com.leo.demo.shopping.models.dto.home.HomeResponse;
import com.leo.demo.shopping.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author leo
 * @date 2023/9/24
 */
@RestController
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private IHomeService homeService;

    public HomeController(IHomeService homeService) {
        this.homeService = homeService;
    }

    @RequestMapping(value = "/hi", method = {RequestMethod.POST})
    public HomeResponse hi(@RequestBody HomeRequest request) {
        return homeService.sayhi(request);
    }
}
