package com.mrhy.resumeserver.controller;


import com.mrhy.common.ObjectResponse;
import com.mrhy.common.vo.resume.ResumeVO;
import com.mrhy.resumeserver.service.IResumeBasicService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author mrhy
 * @since 2020-12-25
 */
@RestController
@RequestMapping("/resume-basic")
@Log4j2
public class ResumeBasicController {
    private IResumeBasicService basicService;

    @Autowired
    public void setBasicService(IResumeBasicService basicService) {
        this.basicService = basicService;
    }

    @ApiOperation("保存简历")
    @PostMapping("/save")
    public ObjectResponse saveResume(@RequestBody ResumeVO resumeVO) {
        log.info("controller:保存简历[{}]", resumeVO);
        basicService.saveResume(resumeVO);
        return new ObjectResponse("保存成功");
    }

}

