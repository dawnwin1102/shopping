package com.leo.demo.shopping.advice;

import com.leo.demo.shopping.models.base.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author leo
 * @date 2023/8/10
 */
@Slf4j
@RestControllerAdvice
public class CommonResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return !methodParameter.getParameterType().getSimpleName().equals("ResponseEntity");
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        BaseResponse baseResponse = null;
        if (o instanceof BaseResponse) {
            baseResponse = (BaseResponse) o;
        } else {
            baseResponse = new BaseResponse();
            baseResponse.setResult(o);
        }

        log.info("Response body:{} ", baseResponse);
        return baseResponse;
    }
}
