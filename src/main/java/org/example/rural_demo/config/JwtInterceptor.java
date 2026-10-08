package org.example.rural_demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.rural_demo.common.BusinessException;
import org.example.rural_demo.common.JwtUtil;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        // 从请求头获取token
        String token = request.getHeader("token");
        if (token == null || token.isBlank()) {
            throw new BusinessException(401,"未登录，请先登录");
        }
        if (!JwtUtil.validateToken(token)) {
            throw new BusinessException(401,"token无效或已过期，请重新登录");
        }
        // 解析token，拿到用户id和角色，存入request，Controller可以直接获取
        Long userId = JwtUtil.getUserId(token);
        Integer role = JwtUtil.getRole(token);
        request.setAttribute("userId", userId);
        request.setAttribute("role", role);
        return true;
    }
}