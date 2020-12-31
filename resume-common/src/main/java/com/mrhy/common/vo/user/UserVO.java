package com.mrhy.common.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * userVo
 *
 * @author mrhy
 * @date 2020/12/22 20:58
 * Copyright (C), 2018-2020
 */
@Data
@ApiModel(description = "用户注册")
public class UserVO implements Serializable {
    @ApiModelProperty("账号")
    @Size(max = 20, message = "账户不能超过20位")
    String account;
    @ApiModelProperty("密码")
    @NotBlank(message = "密码不能为空")
    @Min(value = 6, message = "密码最少为6位")
    String password;
    @ApiModelProperty("邮箱")
    @Email(message = "请输入正确的邮箱格式")
    String email;
    @ApiModelProperty("手机号")
    @NotNull(message = "手机号不能为空")
    String phone;
}
