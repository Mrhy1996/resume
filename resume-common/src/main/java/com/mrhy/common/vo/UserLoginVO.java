package com.mrhy.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 登录
 *
 * @author mrhy
 * @date 2020/12/24 14:10
 * Copyright (C), 2018-2020
 */
@Data
@ApiModel
public class UserLoginVO {
    @ApiModelProperty("账户")
    @Size(max = 20, message = "账户不能超过20位")
    private String account;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    private String password;
}
