package com.mrhy.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 操作符
 *
 * @author mrhy
 */
@AllArgsConstructor
@Getter
public enum OperationFlag {
    /**
     * 成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 失败
     */
    FAIL(-1, "操作失败"),
    /**
     * 参数违法
     */
    ILLEGAL_ARGUMENT(-2, "参数违法"),
    /**
     * 未登录
     */
    NOT_LOGIN(401, "未登录"),
    /**
     * 服务降级
     */
    SERVICE_DEGRADATION(100, "服务降级"),
    /**
     * 接口限流
     */
    INTERFACE_LIMITING(101, "接口限流"),
    /**
     * 热电参数限流
     */
    HOT_PARAMETER_LIMITING(102, "热点参数限流"),
    /**
     * 触发系统保护规则
     */
    TRIGGER_SYSTEM_PROTECT_ROLE(103, "触发系统保护规则"),
    /**
     * 授权规则不通过
     */
    AUTHORIZATION_RULES(104, "授权规则不通过"),
    ;

    private final Integer returnCode;

    private final String description;
}
