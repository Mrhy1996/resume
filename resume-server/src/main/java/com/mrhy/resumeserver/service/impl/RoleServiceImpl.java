package com.mrhy.resumeserver.service.impl;

import com.mrhy.resumeserver.entity.Role;
import com.mrhy.resumeserver.mapper.RoleMapper;
import com.mrhy.resumeserver.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
