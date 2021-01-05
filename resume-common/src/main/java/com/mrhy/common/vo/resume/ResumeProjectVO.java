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
public class ResumeProjectVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 简历列表id
     */
    private Integer resumeListId;

    /**
     * 项目名称
     */
    private String projectName;

    /**
     * 角色
     */
    private String role;

    /**
     * 项目简介
     */
    private String projectIntroduce;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

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
