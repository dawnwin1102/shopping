package com.leo.demo.shopping.advice;

import com.leo.demo.shopping.annotation.NeedLogin;
import com.leo.demo.shopping.exception.BusinessException;
import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.base.ResponseCodeEnum;
import com.leo.demo.shopping.models.entities.User;
import com.leo.demo.shopping.util.CookieParamUtil;
import com.leo.demo.shopping.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import java.io.IOException;
import java.lang.reflect.Type;


/**
 * @author leo
 * @date 2023/7/3
 */
@RestControllerAdvice
@RequiredArgsConstructor
@Component
@Slf4j
@Order(1)
public class NeedLoginAdvice implements RequestBodyAdvice {


    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return methodParameter.hasMethodAnnotation(NeedLogin.class);
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        return inputMessage;
    }

    @Override
    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        BaseRequest baseRequest = (BaseRequest) body;
        if (baseRequest == null) {
            baseRequest = new BaseRequest();
        }
        var token = CookieParamUtil.getAuthToken();
        User user = JWTUtil.getUser(token);
        if (user == null) {
            throw new BusinessException(ResponseCodeEnum.Code_3001);
        }
        baseRequest.setUserName(user.getName());
        baseRequest.setMobile(user.getMobile());
        return baseRequest;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}


