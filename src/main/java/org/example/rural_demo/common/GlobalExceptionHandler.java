package org.example.rural_demo.common;


import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 统一捕获所有异常，自动返回Result.error()
 * Controller里不用写try-catch
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    //捕获业务异常
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }
    //捕获其他所有异常
    @ExceptionHandler(Exception.class)
    public Result<Void> handleException(Exception e) {
        //打印异常信息到控制台，方便排查问题
        e.printStackTrace();
        return Result.error(500, "服务器内部错误");
    }


}
