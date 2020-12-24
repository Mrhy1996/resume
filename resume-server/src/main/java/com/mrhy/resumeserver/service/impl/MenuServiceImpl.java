package com.mrhy.resumeserver.service.impl;

import com.mrhy.resumeserver.entity.Menu;
import com.mrhy.resumeserver.mapper.MenuMapper;
import com.mrhy.resumeserver.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
