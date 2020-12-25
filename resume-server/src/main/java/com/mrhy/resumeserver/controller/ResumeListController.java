package com.mrhy.resumeserver.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrhy.common.ObjectResponse;
import com.mrhy.common.enums.DeleteStatusEnum;
import com.mrhy.resumeserver.entity.ResumeList;
import com.mrhy.resumeserver.service.IResumeListService;
import io.swagger.annotations.Api;
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
@RequestMapping("/resume-list")
@Api(value = "简历列表",tags = "简历")
@Log4j2
public class ResumeListController {
    @Autowired
    public void setResumeListService(IResumeListService resumeListService) {
        this.resumeListService = resumeListService;
    }

    private IResumeListService resumeListService;

    /**
     * 查看简历列表
     *
     * @author mrhy
     * @date 2020/12/25 23:14
     */
    @GetMapping("/list")
    @ApiOperation("查看列表")
    public ObjectResponse getList(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询简历总览列表：pageNum=[{}],pageSize=[{}]", pageNum, pageSize);
        Page<ResumeList> page = new Page<>(pageNum, pageSize);
        QueryWrapper<ResumeList> wrapper = new QueryWrapper<>();
        wrapper.eq("delete_status", DeleteStatusEnum.NO_DELETED.getItemCode());
        Page<ResumeList> resumeListPage = resumeListService.page(page, wrapper);
        log.info("简历列表查询成功");
        return new ObjectResponse(resumeListPage);
    }

    /**
     * 删除简历
     *
     * @author mrhy
     * @date 2020/12/25 23:14
     */
    @PostMapping("del")
    @ApiOperation("删除简历")
    public ObjectResponse deleteResume(@RequestParam Integer id) {
        log.info("删除简历，id={}", id);
        UpdateWrapper<ResumeList> wrapper = new UpdateWrapper<>();
        wrapper.set("delete_status", DeleteStatusEnum.DELETED.getItemCode());
        wrapper.eq("id", id);
        resumeListService.update(wrapper);
        log.info("删除id={}的简历成功", id);
        return new ObjectResponse();
    }

}

