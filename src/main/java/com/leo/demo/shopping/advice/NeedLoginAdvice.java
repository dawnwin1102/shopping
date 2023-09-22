package com.leo.demo.shopping.advice;

import cn.hutool.core.util.StrUtil;
import com.leo.demo.shopping.annotation.NeedLogin;
import com.leo.demo.shopping.exception.BusinessException;
import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.util.CookieParamUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
//        ProfileAuthParams params = CookieParamUtil.getAuthParams(env);
//        boolean isCartRequest = CART.equals(parameter.getMethodAnnotation(NeedLogin.class).value());
//        // cart process not need login/visitor
//        if (isCartRequest &&
//                StrUtil.isBlank(params.getActiveSessionId()) &&
//                StrUtil.isBlank(params.getShdrVisitorModelFlag())) {
//            return baseRequest;
//        }
//        if ("SupportSign".equals(parameter.getMethodAnnotation(NeedLogin.class).value())
//                && StrUtil.isNotBlank(baseRequest.getSign())) {
//            return baseRequest;
//        }
//        CookieParamUtil.setGeneralInfo(baseRequest, params);
//        if (baseRequest.getAuthInfo().isVisitorModel()) {
//            if (StringUtils.isBlank(params.getShdrVisitorId())) {
//                throw new BusinessException(LogModuleEnum.Service, ResponseCodeEnum.Code_1001.getCode(), ResponseCodeEnum.Code_1001.getDescription());
//            }
//            baseRequest.setSwid(params.getShdrVisitorId());
//            return baseRequest;
//        } else {
//            profileService.setAuthInfoBySession(baseRequest, false);
//        }

        return baseRequest;
    }

    @Override
    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}


