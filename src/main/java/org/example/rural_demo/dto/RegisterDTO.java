package org.example.rural_demo.dto;

/**
 * 注册参数DTO
 * 接收前端传过来的注册信息
 */
public class RegisterDTO {
    //用户名
    private String username;
    //密码
    private String password;
    //真实姓名
    private String realName;
    //手机号
    private String phone;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
