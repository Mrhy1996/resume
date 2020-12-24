package com.mrhy.resumeweb.support;

import com.mrhy.common.BusinessException;
import com.mrhy.common.ObjectResponse;
import com.mrhy.common.OperationFlag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @Author mrhy
 * @Description 全局异常拦截
 * @Date 2020-05-14 22:01
 * @Param
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 捕捉自定义异常
     *
     * @author mrhy
     * @date 2020/10/6 9:44 上午
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public ObjectResponse businessExceptionHandler(BusinessException e) {
        log.error("自定义异常",e);
        return new ObjectResponse(e.getErrorCode(), e.getMessage());
    }

    /**
     * 捕捉参数不正确的异常
     *
     * @author mrhy
     * @date 2020/10/6 9:43 上午
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ObjectResponse illegalArgumentExceptionHandler(IllegalArgumentException e) {
        log.error("参数不合法", e);
        return new ObjectResponse(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode(), "传参不正确:" + e.getMessage());
    }

    /**
     * 捕捉实体类的异常
     *
     * @author mrhy
     * @date 2020/10/6 9:43 上午
     */
    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseBody
    public ObjectResponse paramExceptionHandler(MethodArgumentNotValidException e) {
        BindingResult exceptions = e.getBindingResult();
        ObjectResponse objectResponse = new ObjectResponse();
//        // 判断异常中是否有错误信息，如果存在就使用异常中的消息，否则使用默认消息
        if (exceptions.hasErrors()) {
            List<ObjectError> errors = exceptions.getAllErrors();
            if (!errors.isEmpty()) {
                // 这里列出了全部错误参数，按正常逻辑，只需要第一条错误即可
                FieldError fieldError = (FieldError) errors.get(0);
                objectResponse.setDescription(fieldError.getDefaultMessage());
                objectResponse.setReturnCode(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode());
                log.error("入参不合法:{}", fieldError.getDefaultMessage(), e);
            }
        }
        return objectResponse;
    }

    /**
     * 捕捉非实体类的异常
     *
     * @author mrhy
     * @date 2020/10/6 9:42 上午
     */
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ObjectResponse paramExceptionHandler(ConstraintViolationException e) {
        log.error("参数不合法", e);
        return new ObjectResponse(OperationFlag.ILLEGAL_ARGUMENT.getReturnCode(), e.getMessage());
    }


}