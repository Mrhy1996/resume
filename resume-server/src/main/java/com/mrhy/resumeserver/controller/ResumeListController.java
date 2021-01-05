package com.mrhy.resumeserver.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mrhy.common.BusinessException;
import com.mrhy.common.ObjectResponse;
import com.mrhy.common.enums.DeleteStatusEnum;
import com.mrhy.common.vo.resume.ResumeListVO;
import com.mrhy.resumeserver.entity.ResumeList;
import com.mrhy.resumeserver.service.IResumeListService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

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
        wrapper.orderByDesc("insert_time");
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
    @ApiOperation("删除简历(假删)")
    public ObjectResponse deleteResume(@RequestParam Integer id) {
        log.info("删除简历，id={}", id);
        UpdateWrapper<ResumeList> wrapper = new UpdateWrapper<>();
        wrapper.set("delete_status", DeleteStatusEnum.DELETED.getItemCode());
        wrapper.eq("id", id);
        resumeListService.update(wrapper);
        log.info("删除id={}的简历成功", id);
        return new ObjectResponse();
    }

    /**
     * 插入简历列表
     *
     * @author mrhy
     * @date 2020/12/26 08:27
     */
    @PostMapping("/resume")
    @ApiOperation("插入简历列表")
    public ObjectResponse insertResume(@RequestBody @Validated(ResumeListVO.Save.class) ResumeListVO resumeListVO) {
        log.info("插入简历，{}", resumeListVO);
//        查询名称是否重复
        String name = resumeListVO.getName();
        ResumeList resumeListServiceOne = resumeListService.getOne(new QueryWrapper<ResumeList>()
                .eq("name", name)
                .eq("user_id", 1)
                .eq("delete_status", DeleteStatusEnum.NO_DELETED.getItemCode()));
        if (!Objects.isNull(resumeListServiceOne)) {
            throw new BusinessException("简历名称重复!");
        }
        ResumeList resumeList = new ResumeList();
        BeanUtils.copyProperties(resumeListVO, resumeList);
        resumeList.setDeleteStatus(DeleteStatusEnum.DELETED.getItemCode());
        // TODO 写死用户id
        resumeList.setUserId(1);
        String uuid = UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
        resumeList.setUuid(uuid);
        resumeListService.save(resumeList);
        return new ObjectResponse(uuid);
    }

    /**
     * 更新简历
     *
     * @author mrhy
     * @date 2021/1/1 14:58
     */
    @PostMapping("/update")
    @ApiOperation("更新简历")
    public ObjectResponse updateResume(@RequestBody @Validated(ResumeListVO.Update.class) ResumeListVO resumeListVO) {
        log.info("更新简历，{}", resumeListVO);
        ResumeList resumeList = new ResumeList();
        BeanUtils.copyProperties(resumeListVO, resumeList);
        resumeListService.updateById(resumeList);
        return new ObjectResponse();
    }

    /**
     * 根据uuid获取简历
     *
     * @author mrhy
     * @date 2021/1/1 14:54
     */
    @GetMapping("/oneByUuid")
    @ApiOperation("根据uuid获取唯一的简历列表")
    public ObjectResponse getOneByUuid(@RequestParam String uuid) {
        log.info("根据uuid获取唯一的简历列表，uuid={}", uuid);
        QueryWrapper<ResumeList> wrapper = new QueryWrapper<>();
        wrapper.eq("uuid", uuid);
        wrapper.eq("delete_status", DeleteStatusEnum.DELETED.getItemCode());
        ResumeList resumeList = resumeListService.getOne(wrapper);
        if (Objects.isNull(resumeList)) {
            throw new BusinessException("违法的uuid");
        }
        return new ObjectResponse(resumeList);
    }


}

