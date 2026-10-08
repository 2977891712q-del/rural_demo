package org.example.rural_demo.controller;

import org.example.rural_demo.common.JwtUtil;
import org.example.rural_demo.common.Result;
import org.example.rural_demo.dto.LoginDTO;
import org.example.rural_demo.dto.RegisterDTO;
import org.example.rural_demo.entity.User;
import org.example.rural_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户Controller
 * 处理登录、注册等用户相关的请求
 */
@RestController
@RequestMapping("/user")
public class UserController {

    //注入UserService
    @Autowired
    private UserService userService;

    //用户登录，返回Token和用户信息
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginDTO loginDTO) {
        User user = userService.login(loginDTO);
        //生成Token
        String token = JwtUtil.generateToken(user.getId(), user.getRole());
        //把Token和用户信息一起返回
        Map<String, Object> result = new HashMap<>();
        result.put("token", token);
        result.put("user", user);
        return Result.success(result);
    }

    //用户注册
    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterDTO registerDTO) {
        userService.register(registerDTO);
        return Result.success();
    }
}
