package com.leo.demo.shopping.util;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * @author leo
 * @date 2023/7/6
 */
@Slf4j
public class CookieParamUtil {

    public static final String AUTHTOKEN = "auth_token";

    public static String getAuthToken() {
        HttpServletRequest request = getCurrentRequest();
        Cookie[] cookies = request.getCookies();
        log.info("cookies:{}", JSON.toJSONString(cookies));
        String authToken = "";
        if (cookies != null) {
            var authCookie = Arrays.stream(cookies).filter(cookie -> cookie.getName().equals(AUTHTOKEN)).findAny();
            if (authCookie.isPresent()) {
                authToken = authCookie.get().getValue();
            }
        }

        log.info("Request authToken:{}", authToken);
        return authToken;
    }

    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest();
        }
        throw new IllegalStateException("No current HttpServletRequest found");
    }
}
