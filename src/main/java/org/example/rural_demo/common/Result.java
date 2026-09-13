package org.example.rural_demo.common;

/**
 * 统一返回结果类
 * 所有接口都返回这个格式
 * { code:状态码, msg:提示信息, data:返回数据 }
 */
public class Result <T>{
    //状态码 200成功 其他失败
    private Integer code;
    //提示信息
    private String msg;
    //返回的数据
    private T data;

    //无参构造方法
    public Result() {
    }
    //全参构造方法
    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    //成功返回（带数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }
    //成功返回（不带数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }
    //失败返回
    public static <T> Result<T> error(Integer code, String msg) {
        return new Result<>(code, msg, null);
    }
    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
