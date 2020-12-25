package com.mrhy.common.enums;

/**
 * 删除状态
 *
 * @author mrhy
 * @date 2020/12/25 23:06
 * Copyright (C), 2018-2020
 */
public enum DeleteStatusEnum {
    NO_DELETED(0, "未删除"),
    DELETED(1, "已删除"),
    ;

    private final Integer itemCode;
    private final String itemName;

    DeleteStatusEnum(Integer itemCode, String itemName) {
        this.itemCode = itemCode;
        this.itemName = itemName;
    }

    public Integer getItemCode() {
        return itemCode;
    }

    public String getItemName() {
        return itemName;
    }
}
