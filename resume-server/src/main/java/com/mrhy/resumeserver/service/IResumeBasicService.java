package com.mrhy.resumeserver.service;

import com.mrhy.common.vo.resume.ResumeVO;
import com.mrhy.resumeserver.entity.ResumeBasic;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author mrhy
 * @since 2020-12-25
 */
public interface IResumeBasicService extends IService<ResumeBasic> {
    /**
     * 保存简历
     * @author mrhy
     * @date 2020/12/29 21:58
     * @param resumeVO
    */
    void saveResume(ResumeVO resumeVO);

}
