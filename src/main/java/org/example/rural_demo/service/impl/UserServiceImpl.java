package org.example.rural_demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.example.rural_demo.common.BusinessException;
import org.example.rural_demo.dto.LoginDTO;
import org.example.rural_demo.dto.RegisterDTO;
import org.example.rural_demo.entity.User;
import org.example.rural_demo.mapper.UserMapper;
import org.example.rural_demo.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

/**
 * 用户Service实现类
 * 实现登录、注册等业务逻辑
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //用户登录
    @Override
    public User login(LoginDTO loginDTO) {
        //1. 根据用户名查询用户
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, loginDTO.getUsername());
        User user = this.getOne(wrapper);

        //2. 判断用户是否存在
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        //3. 判断密码是否正确（密码用MD5加密存储，所以要加密后再比较）
        String md5Password = DigestUtils.md5DigestAsHex(loginDTO.getPassword().getBytes());
        if (!user.getPassword().equals(md5Password)) {
            throw new BusinessException("密码错误");
        }

        //4. 登录成功，返回用户信息
        return user;
    }

    //用户注册
    @Override
    public void register(RegisterDTO registerDTO) {
        //1. 判断用户名是否已存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, registerDTO.getUsername());
        Long count = this.count(wrapper);
        if (count > 0) {
            throw new BusinessException("用户名已存在");
        }

        //2. 创建用户对象，设置字段
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        //密码用MD5加密存储
        String md5Password = DigestUtils.md5DigestAsHex(registerDTO.getPassword().getBytes());
        user.setPassword(md5Password);
        user.setRealName(registerDTO.getRealName());
        user.setPhone(registerDTO.getPhone());
        //默认角色是村民（0）
        user.setRole(0);

        //3. 保存到数据库
        this.save(user);
    }
}
