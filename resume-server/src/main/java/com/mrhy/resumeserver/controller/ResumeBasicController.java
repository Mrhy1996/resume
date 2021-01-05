package com.mrhy.resumeserver.controller;


import com.mrhy.common.ObjectResponse;
import com.mrhy.common.vo.resume.ResumeVO;
import com.mrhy.resumeserver.service.IResumeBasicService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 保存简历
     *
     * @author mrhy
     * @date 2021/1/4 21:13
     */
    @ApiOperation("保存简历")
    @PostMapping("/save")
    public ObjectResponse saveResume(@RequestBody ResumeVO resumeVO) {
        log.info("controller:保存简历[{}]", resumeVO);
        basicService.saveResume(resumeVO);
        return new ObjectResponse("保存成功");
    }

    /**
     * 查询简历
     *
     * @author mrhy
     * @date 2021/1/4 21:13
     */
    @ApiOperation("查询简历")
    @GetMapping("/info")
    public ObjectResponse getResume(@RequestParam Integer id) {
        log.info("controller:查询简历，简历id={}", id);
        ResumeVO resume = basicService.getResume(id);
        return new ObjectResponse(resume);
    }

    @ApiOperation("更新简历")
    @PostMapping("update")
    public ObjectResponse updateResume(@RequestBody ResumeVO resumeVO) {
        log.info("controller:更新简历，入参为：{}", resumeVO);
        basicService.updateResume(resumeVO);
        return new ObjectResponse("更新简历成功");
    }


}

