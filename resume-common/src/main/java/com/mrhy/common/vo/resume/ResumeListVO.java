package com.mrhy.common.vo.resume;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 简历的VO
 *
 * @author mrhy
 * @date 2020/12/26 07:56
 * Copyright (C), 2018-2020
 */
@Data
@ApiModel(description = "简历列表的VO")
public class ResumeListVO {

    @ApiModelProperty("id")
    @NotNull(message = "id不能为空",groups = {Update.class})
    private Integer id;


    /**
     * 简历名称
     */
    @ApiModelProperty("简历名称")
    @NotNull(message = "简历名称不能为空",groups = {Save.class,Update.class})
    private String name;

    /**
     * 标签
     */
    @ApiModelProperty("简历标签")
    private String tag;

    public interface Save {

    }

    public interface Update {

    }


}
