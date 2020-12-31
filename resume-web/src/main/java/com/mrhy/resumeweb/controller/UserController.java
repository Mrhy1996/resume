package com.mrhy.resumeweb.controller;

import com.mrhy.common.ObjectResponse;
import com.mrhy.common.vo.user.UserLoginVO;
import com.mrhy.common.vo.user.UserVO;
import com.mrhy.resumeweb.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @author mrhy
 * @date 2020/12/22 21:50
 * Copyright (C), 2018-2020
 */
@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    IUserService userService;

    @Autowired
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }


    /**
     * 注册
     *
     * @author mrhy
     * @date 2020/12/23 22:28
     */
    @PostMapping("/register")
    @ApiOperation("注册功能")
    public ObjectResponse register(@RequestBody @Validated UserVO userVO) {
        return userService.register(userVO);
    }
    /**
     * 登录
     * @author mrhy
     * @date 2020/12/24 15:28
    */
    @PostMapping("/login")
    @ApiOperation("登录功能")
    public ObjectResponse login(@RequestBody @Validated UserLoginVO userLoginVO) {
        return userService.login(userLoginVO);
    }
}
