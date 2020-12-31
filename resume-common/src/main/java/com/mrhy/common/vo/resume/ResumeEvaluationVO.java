package com.mrhy.common.vo.resume;

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
 * @since 2020-12-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ResumeEvaluationVO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 简历列表id
     */
    private Integer resumeListId;

    /**
     * 自我评价
     */
    private String evaluation;

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
