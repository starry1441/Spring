package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -04
 * Time: 15:16
 */

@Configuration
public class AppConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
    }

    //配置拦截规则
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**") //拦截所有接口
                .excludePathPatterns("/api/user/login") //不拦截登录接口
                .excludePathPatterns("/api/user/reg")   //不拦截注册接口
                .excludePathPatterns("/login.html") //不拦截登录页面
                .excludePathPatterns("/regin.html") //不拦截注册页面
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/**/*.png");
    }

}
