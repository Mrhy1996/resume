package com.mrhy.resumeserver.service;

import com.mrhy.common.vo.resume.ResumeVO;
import com.mrhy.resumeserver.entity.ResumeBasic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-25
 */
public interface IResumeBasicService extends IService<ResumeBasic> {
    /**
     * 保存简历
     *
     * @param resumeVO
     * @author mrhy
     * @date 2020/12/29 21:58
     */
    void saveResume(ResumeVO resumeVO);

    /**
     * 获取简历详情
     *
     * @param resumeListId 简历列表id
     * @return 实体类
     */
    ResumeVO getResume(Integer resumeListId);

    /**
     * 更新简历
     * @author mrhy
     * @param resumeVO  入参
     * @date 2021/1/5 14:29
    */
    void updateResume(ResumeVO resumeVO);

}
