package com.leo.demo.shopping.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import com.alibaba.fastjson.JSON;
import com.leo.demo.shopping.models.base.BaseRequest;
import com.leo.demo.shopping.models.dto.ProfileAuthParams;
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

    public static ProfileAuthParams getAuthParams(String env) {
        HttpServletRequest request = getCurrentRequest();
//        String userAgent = request.getHeader("user-agent");
//        log.info("userAgent:{}", userAgent);
        Cookie[] cookies = request.getCookies();
        log.info("cookies:{}", JSON.toJSONString(cookies));
        ProfileAuthParams params = new ProfileAuthParams();
        String shdrSessionKey = "";
        if (cookies != null) {
            Arrays.stream(cookies).forEach(cookie -> {
                switch (cookie.getName()) {
//                    case SHDR_SESSION:
//                        params.setShdrSession(cookie.getValue());
//                        break;
//                    case SHDR_WEB_SESSION:
//                        params.setShdrWebSession(cookie.getValue());
//                        break;
//                    case SHDR_OPENID:
//                        params.setShdrOpenId(cookie.getValue());
//                        break;
//                    case VISITOR_FLAG:
//                        params.setShdrVisitorModelFlag(cookie.getValue());
//                        break;
//                    case VISITOR_ID:
//                        params.setShdrVisitorId(cookie.getValue());
//                        break;
                }
                // get session with env: shdr-session-latest/shdr-session-stage/shdr-session
                if (cookie.getName().equals(shdrSessionKey)) {
                    params.setShdrSession(cookie.getValue());
                }
            });
        }
        // use shdrSession first
        if (StrUtil.isNotBlank(params.getShdrSession())) {
            params.setPlatform("wechat");
            params.setActiveSessionId(params.getShdrSession());
        } else {
            params.setPlatform("web");
            params.setActiveSessionId(params.getShdrWebSession());
        }
        params.setActiveSessionId(URLUtil.decode(params.getActiveSessionId()));
        log.info("Request ProfileAuthParams:{}", params);
        return params;
    }


    public static void setGeneralInfo(BaseRequest baseRequest, ProfileAuthParams params) {
        if (StrUtil.isBlank(params.getShdrVisitorModelFlag())
                && StrUtil.isNotBlank(params.getActiveSessionId())) {
            baseRequest.getAuthInfo().setVisitorModel(false);
        } else {
            baseRequest.getAuthInfo().setVisitorModel("1".equals(params.getShdrVisitorModelFlag()));
        }
        log.info("VisitorModel:{}", baseRequest.getAuthInfo().isVisitorModel());
        baseRequest.getAuthInfo().setSessionId(params.getActiveSessionId());
        baseRequest.getAuthInfo().setOpenId(params.getShdrOpenId());
        baseRequest.getAuthInfo().setPlatform(params.getPlatform());
        baseRequest.getAuthInfo().setShdrVisitorId(params.getShdrVisitorId());
    }

    private static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest();
        }
        throw new IllegalStateException("No current HttpServletRequest found");
    }
}
