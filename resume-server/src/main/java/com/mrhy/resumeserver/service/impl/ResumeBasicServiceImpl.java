package com.mrhy.resumeserver.service.impl;

import com.mrhy.common.vo.resume.*;
import com.mrhy.resumeserver.entity.*;
import com.mrhy.resumeserver.mapper.*;
import com.mrhy.resumeserver.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-25
 */
@Service
@Log4j2
public class ResumeBasicServiceImpl extends ServiceImpl<ResumeBasicMapper, ResumeBasic> implements IResumeBasicService {
    /**
     * 教育经历
     */
    IResumeEducationService educationService;
    /**
     * 自我评价
     */
    IResumeEvaluationService evaluationService;
    /**
     * 项目经历
     */
    IResumeProjectService projectService;
    /**
     * 技能
     */
    IResumeSkillService skillService;

    @Autowired
    public void setEducationService(IResumeEducationService educationService) {
        this.educationService = educationService;
    }

    @Autowired
    public void setEvaluationService(IResumeEvaluationService evaluationService) {
        this.evaluationService = evaluationService;
    }

    @Autowired
    public void setProjectService(IResumeProjectService projectService) {
        this.projectService = projectService;
    }

    @Autowired
    public void setSkillService(IResumeSkillService skillService) {
        this.skillService = skillService;
    }

    /**
     * 保存项目
     * @author mrhy
     * @date 2020/12/30 14:21
    */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveResume(ResumeVO resumeVO) {
        log.info("简历保存，resumeVO={}", resumeVO);
        ResumeBasicVO basicVO = resumeVO.getBasic();
        ResumeBasic basic = new ResumeBasic();
        BeanUtils.copyProperties(basicVO, basic);
        basic.setResumeListId(resumeVO.getResumeListId());
        baseMapper.insert(basic);
//        教育经历
        List<ResumeEducationVO> educationsVO = resumeVO.getEducations();
        List<ResumeEducation> educations = new ArrayList<>();
        for (ResumeEducationVO resumeEducationVO : educationsVO) {
            ResumeEducation resumeEducation = new ResumeEducation();
            BeanUtils.copyProperties(resumeEducationVO, resumeEducation);
            resumeEducation.setResumeListId(resumeVO.getResumeListId());
            educations.add(resumeEducation);
        }
        educationService.saveBatch(educations);
//        项目经历
        List<ResumeProjectVO> projectsVO = resumeVO.getProjects();
        List<ResumeProject> projects = new ArrayList<>();
        for (ResumeProjectVO item : projectsVO) {
            ResumeProject resumeProject = new ResumeProject();
            BeanUtils.copyProperties(item, resumeProject);
            resumeProject.setResumeListId(resumeVO.getResumeListId());
            projects.add(resumeProject);
        }
        projectService.saveBatch(projects);
//        自我评价
        ResumeEvaluationVO evaluationVO = resumeVO.getEvaluation();
        ResumeEvaluation resumeEvaluation = new ResumeEvaluation();
        BeanUtils.copyProperties(evaluationVO, resumeEvaluation);
        resumeEvaluation.setResumeListId(resumeVO.getResumeListId());
        evaluationService.save(resumeEvaluation);
//        技能
        ResumeSkillVO skillVO = resumeVO.getSkill();
        ResumeSkill resumeSkill = new ResumeSkill();
        BeanUtils.copyProperties(skillVO, resumeSkill);
        resumeSkill.setResumeListId(resumeVO.getResumeListId());
        skillService.save(resumeSkill);
        log.info("保存简历成功");
    }


}
