package com.leo.demo.shopping.controller;


import com.leo.demo.shopping.dummydatabase.UserBean;
import com.leo.demo.shopping.exception.UnauthorizedException;
import com.leo.demo.shopping.impl.UserService;
import com.leo.demo.shopping.models.base.BaseResponse;
import com.leo.demo.shopping.models.base.ResponseCode;
import com.leo.demo.shopping.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PostMapping("/login")
    public BaseResponse login(@RequestParam("username") String username,
                              @RequestParam("password") String password) {
        UserBean userBean = userService.getUser(username);
        if (userBean.getPassword().equals(password)) {
            BaseResponse response = new BaseResponse(ResponseCode.Code_0000);
            response.setResult(JWTUtil.sign(username, password));
            return response;
        } else {
            throw new UnauthorizedException();
        }
    }
}
