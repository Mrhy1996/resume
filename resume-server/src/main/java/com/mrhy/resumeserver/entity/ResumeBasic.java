package com.mrhy.resumeserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author mrhy
 * @since 2020-12-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResumeBasic implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 列表中的id
     */
    private Integer resumeListId;

    /**
     * 角色编码
     */
    private String name;

    /**
     * 菜单编码
     */
    private String sex;

    /**
     * 出生日期
     */
    private LocalDateTime birthday;

    /**
     * 户籍所在地
     */
    @TableField("nativePlace")
    private String nativePlace;

    /**
     * 手机号码前缀
     */
    @TableField("phoneNumberPrefix")
    private String phoneNumberPrefix;

    /**
     * 联系方式
     */
    @TableField("phoneNumber")
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

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
