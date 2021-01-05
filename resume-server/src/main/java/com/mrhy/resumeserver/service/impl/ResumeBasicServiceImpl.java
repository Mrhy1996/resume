package com.mrhy.resumeserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.mrhy.common.enums.DeleteStatusEnum;
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
    /**
     * list 的service
     */
    IResumeListService resumeListService;

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

    @Autowired
    public void setResumeListService(IResumeListService resumeListService) {
        this.resumeListService = resumeListService;
    }

    /**
     * 保存项目
     *
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
//        更新简历状态
        UpdateWrapper<ResumeList> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", resumeVO.getResumeListId());
        updateWrapper.set("delete_status", DeleteStatusEnum.NO_DELETED.getItemCode());
        resumeListService.update(updateWrapper);
        log.info("保存简历成功");
    }

    @Override
    public ResumeVO getResume(Integer resumeListId) {
        log.info("查询简历，resumeListId={}", resumeListId);
        ResumeVO resumeVO = new ResumeVO();
//        基本信息
        ResumeBasic resumeBasic = getOne(new QueryWrapper<ResumeBasic>().eq("resume_list_id", resumeListId));
        ResumeBasicVO resumeBasicVO = new ResumeBasicVO();
        BeanUtils.copyProperties(resumeBasic, resumeBasicVO);
        resumeVO.setBasic(resumeBasicVO);
//        教育经历
        List<ResumeEducation> resumeEducationList = educationService.list(new QueryWrapper<ResumeEducation>().eq("resume_list_id", resumeListId));
        List<ResumeEducationVO> resumeEducationVOList = new ArrayList<>();

        for (ResumeEducation resumeEducation : resumeEducationList) {
            ResumeEducationVO resumeEducationVO = new ResumeEducationVO();
            BeanUtils.copyProperties(resumeEducation, resumeEducationVO);
            resumeEducationVOList.add(resumeEducationVO);
        }
        resumeVO.setEducations(resumeEducationVOList);
//        项目经历
        List<ResumeProject> resumeProjectList = projectService.list(new QueryWrapper<ResumeProject>().eq("resume_list_id", resumeListId));
        List<ResumeProjectVO> resumeProjectVOList = new ArrayList<>();
        for (ResumeProject resumeProject : resumeProjectList) {
            ResumeProjectVO resumeProjectVO = new ResumeProjectVO();
            BeanUtils.copyProperties(resumeProject, resumeProjectVO);
            resumeProjectVOList.add(resumeProjectVO);
        }
        resumeVO.setProjects(resumeProjectVOList);
//        技能
        ResumeSkill resumeSkill = skillService.getOne(new QueryWrapper<ResumeSkill>().eq("resume_list_id", resumeListId));
        ResumeSkillVO resumeSkillVO = new ResumeSkillVO();
        BeanUtils.copyProperties(resumeSkill, resumeSkillVO);
        resumeVO.setSkill(resumeSkillVO);
//        自我评价
        ResumeEvaluation resumeEvaluation = evaluationService.getOne(new QueryWrapper<ResumeEvaluation>().eq("resume_list_id", resumeListId));
        ResumeEvaluationVO evaluationVO = new ResumeEvaluationVO();
        BeanUtils.copyProperties(resumeEvaluation, evaluationVO);
        resumeVO.setEvaluation(evaluationVO);
        resumeVO.setResumeListId(resumeListId);
        return resumeVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateResume(ResumeVO resumeVO) {
        log.info("更新简历，resumeVO={}", resumeVO);
        Integer resumeListId = resumeVO.getResumeListId();
//        先删除
        remove(new QueryWrapper<ResumeBasic>().eq("resume_list_id", resumeListId));
//        删除教育
        educationService.remove(new QueryWrapper<ResumeEducation>().eq("resume_list_id", resumeListId));
//        删除项目
        projectService.remove(new QueryWrapper<ResumeProject>().eq("resume_list_id", resumeListId));
//        删除技能
        skillService.remove(new QueryWrapper<ResumeSkill>().eq("resume_list_id", resumeListId));
//        删除自我评价
        evaluationService.remove(new QueryWrapper<ResumeEvaluation>().eq("resume_list_id", resumeListId));
//        插入新的
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
        log.info("更新简历成功");

    }


}
