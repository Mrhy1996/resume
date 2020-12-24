package com.mrhy.resumeserver.service;

import com.mrhy.common.vo.UserLoginVO;
import com.mrhy.common.vo.UserVO;
import com.mrhy.resumeserver.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
public interface IUserService extends IService<User> {
    /**
     * 注册用户
     *
     * @param userVO 用户请求的实体类
     * @author mrhy
     * @date 2020/12/23 22:58
     */
    void register(UserVO userVO);
    /**
     * 登录
     * @param userLoginVO 用户登录的实体类
     * @author mrhy
     * @date 2020/12/24 14:46
    */
    void login(UserLoginVO userLoginVO);

}
