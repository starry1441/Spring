package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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

    //读取文件中的变量信息
    @Value("${myimgpath}")
    private String imgPath;

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.addPathPrefix("api", c -> true);
    }

    //配置虚拟路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //设置url访问地址，当访问/imges/**映射到虚拟路径上
        registry.addResourceHandler("/imges/**")
                .addResourceLocations("file:" + imgPath); //设置到本地目录
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
//                .excludePathPatterns("/**/**.html") //不拦截所有页面
                .excludePathPatterns("/**/*.css")
                .excludePathPatterns("/**/*.js")
                .excludePathPatterns("/**/*.jpg")
                .excludePathPatterns("/reg_success.html")
                .excludePathPatterns("/reg_err.html")
                .excludePathPatterns("/myblog_list.html")
                .excludePathPatterns("/**/*.png");
    }

}
