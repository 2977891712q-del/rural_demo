package org.example.rural_demo.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.rural_demo.common.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if(token == null || !token.startsWith("Bearer ")){
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"未登录，请先登录\",\"data\":null}");
            return false;
        }
        String realToken = token.substring(7);
        if(!JwtUtil.validateToken(realToken)){
            response.setStatus(401);
            response.getWriter().write("{\"code\":401,\"msg\":\"token无效或已过期\",\"data\":null}");
            return false;
        }
        Long userId = JwtUtil.getUserId(realToken);
        Integer role = JwtUtil.getRole(realToken);
        request.setAttribute("userId",userId);
        request.setAttribute("role",role);
        return true;
    }
}