package com.mrhy.resumeserver.controller;


import com.mrhy.common.ObjectResponse;
import com.mrhy.common.vo.UserLoginVO;
import com.mrhy.common.vo.UserVO;
import com.mrhy.resumeserver.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/user")
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
    public ObjectResponse register(@RequestBody UserVO userVO) {
        ObjectResponse objectResponse = new ObjectResponse();
        userService.register(userVO);
        return objectResponse;
    }
    /**
     * 登录
     *
     * @author mrhy
     * @date 2020/12/23 22:28
     */
    @PostMapping("/login")
    @ApiOperation("登录")
    public ObjectResponse login(@RequestBody UserLoginVO userLoginVO) {
        ObjectResponse objectResponse = new ObjectResponse();
        userService.login(userLoginVO);
        return objectResponse;
    }


}

