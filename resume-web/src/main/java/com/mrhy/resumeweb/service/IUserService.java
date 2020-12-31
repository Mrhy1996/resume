package com.mrhy.resumeweb.service;

import com.mrhy.common.ObjectResponse;
import com.mrhy.common.vo.user.UserLoginVO;
import com.mrhy.common.vo.user.UserVO;
import com.mrhy.resumeweb.service.fallbackfactory.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 用户service
 *
 * @author mrhy
 * @date 2020/12/22 21:44
 * Copyright (C), 2018-2020
 */
@FeignClient(name = "resume-server", path = "/resume/user", fallbackFactory = UserServiceFallBackFactory.class)
public interface IUserService {
    /**
     * 用户注册
     *
     * @param userVO 请求体
     * @return 响应体
     * @author mrhy
     * @date 2020/12/23 23:35
     */
    @PostMapping("register")
    ObjectResponse register(@RequestBody UserVO userVO);
    /**
     * 登录
     * @author mrhy
     * @date 2020/12/24 15:27
    */
    @PostMapping("login")
    ObjectResponse login(@RequestBody UserLoginVO userLoginVO);
}
