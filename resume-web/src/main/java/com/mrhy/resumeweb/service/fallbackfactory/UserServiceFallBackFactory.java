package com.mrhy.resumeweb.service.fallbackfactory;

import com.mrhy.common.ObjectResponse;
import com.mrhy.common.OperationFlag;
import com.mrhy.common.vo.user.UserLoginVO;
import com.mrhy.common.vo.user.UserVO;
import com.mrhy.resumeweb.service.IUserService;
import feign.hystrix.FallbackFactory;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * 用户异常处理类
 *
 * @author mrhy
 * @date 2020/12/22 21:45
 * Copyright (C), 2018-2020
 */
@Service
@Log4j2
public class UserServiceFallBackFactory implements FallbackFactory<IUserService> {
    @Override
    public IUserService create(Throwable throwable) {
        log.error("feign服务调用异常：", throwable);
        return new IUserService() {
            @Override
            public ObjectResponse register(UserVO userVO) {
                return new ObjectResponse(OperationFlag.FAIL.getReturnCode(), OperationFlag.FAIL.getDescription());
            }

            @Override
            public ObjectResponse login(UserLoginVO userLoginVO) {
                return new ObjectResponse(OperationFlag.FAIL.getReturnCode(), OperationFlag.FAIL.getDescription());
            }
        };
    }
}
