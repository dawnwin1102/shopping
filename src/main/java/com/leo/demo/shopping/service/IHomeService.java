package com.leo.demo.shopping.service;


import com.leo.demo.shopping.models.dto.home.HomeRequest;
import com.leo.demo.shopping.models.dto.home.HomeResponse;

/**
 * @author leo
 * @date 2023/1/17
 */
public interface IHomeService {
    HomeResponse sayhi(HomeRequest request);
}
