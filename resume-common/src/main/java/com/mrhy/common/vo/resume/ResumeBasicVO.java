package com.mrhy.common.vo.resume;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class ResumeBasicVO implements Serializable {

    private static final long serialVersionUID = 1L;

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
    private String nativePlace;

    /**
     * 手机号码前缀
     */
    private String phoneNumberPrefix;

    /**
     * 联系方式
     */
    private String phoneNumber;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 插入时间
     */
    @JsonIgnore
    private LocalDateTime insertTime;

    /**
     * 更新时间
     */
    @JsonIgnore
    private LocalDateTime lastUpdateTime;

    /**
     * 更新人
     */
    private String lastUpdateUser;


}
