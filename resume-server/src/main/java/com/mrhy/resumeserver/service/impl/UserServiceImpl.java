package com.mrhy.resumeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.mrhy.common.BusinessException;
import com.mrhy.common.enums.RoleEnum;
import com.mrhy.common.vo.UserLoginVO;
import com.mrhy.common.vo.UserVO;
import com.mrhy.resumeserver.entity.User;
import com.mrhy.resumeserver.entity.UserRole;
import com.mrhy.resumeserver.mapper.UserMapper;
import com.mrhy.resumeserver.mapper.UserRoleMapper;
import com.mrhy.resumeserver.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
@Service
@Log4j2
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserVO userVO) {
        log.info("注册用户：userVO={}", userVO);
//        保存用户
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        save(user);
//        赋权
        log.info("添加新用户结束，id={}", user.getId());
        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleCode(RoleEnum.USER.getItemCode());
        userRoleMapper.insert(userRole);
    }

    @Override
    public void login(UserLoginVO userLoginVO) {
        String account = userLoginVO.getAccount();
        String password = userLoginVO.getPassword();
        log.info("用户登录：account=[{}]", account);
        Wrapper<User> queryWrapper = new QueryWrapper<User>().eq("account", account);
        User user = getOne(queryWrapper);
        if (user == null) {
            throw new BusinessException("该账户不存在");
        }
        if (!password.equals(user.getPassword())) {
            throw new BusinessException("账户或者密码错误");
        }
        log.info("[{}]登录成功", account);
    }
}
