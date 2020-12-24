package com.mrhy.common;

/**
 * @author : Luyz
 * @date : 2020/5/5 07:31
 */
public class BusinessException extends RuntimeException {

    private Integer errorCode;
    private String message;

    public BusinessException(String message) {
        this.message = message;
        this.errorCode = OperationFlag.FAIL.getReturnCode();
    }

    public BusinessException(OperationFlag operationFlag) {
        this.message = operationFlag.getDescription();
        this.errorCode = operationFlag.getReturnCode();
    }


    public BusinessException(Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
