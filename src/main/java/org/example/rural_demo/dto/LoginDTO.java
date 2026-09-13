package org.example.rural_demo.dto;

/**
 * 登录参数DTO
 * 接收前端传过来的用户名和密码
 */
public class LoginDTO {
    //用户名
    private String username;
    //密码
    private String password;

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
}
