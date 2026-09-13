package org.example.rural_demo.common;

/**
 * 自定义业务异常类
 * Service层抛异常用的
 * 比如用户不存在、密码错误
 */
public class BusinessException extends RuntimeException{
    //错误状态码
    private Integer code;

    //构造方法，默认错误码400
    public BusinessException(String message) {
        super(message);
        this.code = 400;
    }

    //构造方法，自定义错误码
    public BusinessException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
