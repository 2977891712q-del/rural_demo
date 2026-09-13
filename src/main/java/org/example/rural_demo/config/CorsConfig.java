package org.example.rural_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 跨域配置
 * 允许前端小程序访问后端接口
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    //配置跨域规则
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                //允许所有来源的前端访问
                .allowedOriginPatterns("*")
                //允许所有请求头
                .allowedHeaders("*")
                //允许所有请求方法（GET、POST、PUT、DELETE等）
                .allowedMethods("*")
                //允许携带cookie
                .allowCredentials(true)
                //预检请求缓存时间，3600秒内不用重复发预检请求
                .maxAge(3600);
    }
}
