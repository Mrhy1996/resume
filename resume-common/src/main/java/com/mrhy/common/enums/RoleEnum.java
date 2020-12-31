package com.mrhy.common.enums;

/**
 * 用户角色的枚举
 *
 * @author mrhy
 * @date 2020/12/23 22:48
 * Copyright (C), 2018-2020
 */
public enum RoleEnum {
    ADMIN("A100030001", "管理员"),
    USER("A100030002", "普通用户"),
    PASSENGER("A100030003", "游客"),
    ;
    private final String itemCode;
    private final String itemName;

    RoleEnum(String itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }
}
