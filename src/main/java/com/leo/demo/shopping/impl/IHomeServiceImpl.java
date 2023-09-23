package com.leo.demo.shopping.impl;


import com.leo.demo.shopping.models.dto.home.HomeRequest;
import com.leo.demo.shopping.models.dto.home.HomeResponse;
import com.leo.demo.shopping.service.IHomeService;
import org.springframework.stereotype.Service;

/**
 * @author leo
 * @date 2023/9/24
 */
@Service
public class IHomeServiceImpl implements IHomeService {
    @Override
    public HomeResponse sayhi(HomeRequest request) {
        HomeResponse homeResponse = new HomeResponse();
        homeResponse.setReplay("hello");
        return homeResponse;
    }
}
