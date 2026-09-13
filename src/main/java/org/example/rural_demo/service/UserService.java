package org.example.rural_demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.example.rural_demo.dto.LoginDTO;
import org.example.rural_demo.dto.RegisterDTO;
import org.example.rural_demo.entity.User;

/**
 * 用户Service接口
 * 继承IService，自带基础的增删改查
 */
public interface UserService extends IService<User> {

    //用户登录，返回用户信息
    User login(LoginDTO loginDTO);

    //用户注册
    void register(RegisterDTO registerDTO);
}
