package com.mrhy.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 响应实体类
 * @author mrhy
 */
@ApiModel("响应体")
public class ObjectResponse {
    @ApiModelProperty("状态码")
    private Integer returnCode;
    @ApiModelProperty("描述")
    private String description;
    @ApiModelProperty("结果")
    private Object result;

    public ObjectResponse() {
        this.returnCode = OperationFlag.SUCCESS.getReturnCode();
        this.description = OperationFlag.SUCCESS.getDescription();
    }

    public ObjectResponse(Integer returnCode) {
        this(returnCode, (String) null);
    }

    public ObjectResponse(Object result) {
        this.returnCode = OperationFlag.SUCCESS.getReturnCode();
        this.description = OperationFlag.SUCCESS.getDescription();
        this.result = result;
    }

    public ObjectResponse(Integer returnCode, String description) {
        this(returnCode, description, (Object) null);
    }

    public ObjectResponse(Integer returnCode, String description, Object result) {
        this.returnCode = returnCode;
        this.description = description;
        this.result = result;
    }

    public Integer getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(Integer returnCode) {
        this.returnCode = returnCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }
}

