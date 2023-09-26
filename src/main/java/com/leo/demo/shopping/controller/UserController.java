package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.dummydatabase.UserBean;
import com.leo.demo.shopping.exception.UnauthorizedException;
import com.leo.demo.shopping.impl.UserService;
import com.leo.demo.shopping.models.base.BaseResponse;
import com.leo.demo.shopping.models.base.ResponseCode;
import com.leo.demo.shopping.models.entities.User;
import com.leo.demo.shopping.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static com.leo.demo.shopping.util.CookieParamUtil.AUTHTOKEN;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public BaseResponse login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserBean userBean = userService.getUser(username);
        Map<String, String> map = new HashMap<>();
        map.put("userName", userBean.getUsername());
        map.put("mobile", userBean.getMobile());

        if (userBean.getPassword().equals(password)) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletResponse response = attributes.getResponse();
            Cookie accessToken = new Cookie(AUTHTOKEN, JWTUtil.sign(map, password));
            accessToken.setMaxAge((int) TimeUnit.DAYS.toSeconds(1));
            accessToken.setPath("/");
            response.addCookie(accessToken);
            BaseResponse baseResponse = new BaseResponse(ResponseCode.Code_0000);
            baseResponse.setResult("success");
            return baseResponse;
        } else {
            throw new UnauthorizedException();
        }
    }
}
