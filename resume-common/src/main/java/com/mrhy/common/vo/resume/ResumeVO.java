package com.mrhy.common.vo.resume;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 简历保存
 *
 * @author mrhy
 * @date 2020/12/30 11:17
 * Copyright (C), 2018-2020
 */
@Data
@ApiModel(description = "简历保存和查看")
public class ResumeVO implements Serializable {

    /**
     * listId
     */
    private Integer resumeListId;

    /**
     * 基本信息
     */
    private ResumeBasicVO basic;
    /**
     * 教育经历
     */
    private List<ResumeEducationVO> educations;
    /**
     * 项目经历
     */
    private List<ResumeProjectVO> projects;
    /**
     * 技能
     */
    private ResumeSkillVO skill;
    /**
     * 自我评价
     */
    private ResumeEvaluationVO evaluation;
}
