package com.mrhy.resumeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 菜单表
 * </p>
 *
 * @author mrhy
 * @since 2020-12-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 菜单编号
     */
    private String menuCode;

    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 父类编号
     */
    private String parentCode;

    /**
     * url
     */
    private String url;

    /**
     * 排序
     */
    private Integer order;

    /**
     * 父级目录0，1级目录1
     */
    private Integer type;

    /**
     * 插入时间
     */
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    private LocalDateTime lastUpdateTime;

    /**
     * 更新人
     */
    private String lastUpdateUser;


}
