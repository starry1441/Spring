package org.example.controller;

import lombok.Getter;
import org.example.model.User;
import org.example.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -20
 * Time: 21:08
 */

@Controller
@Getter
public class LoginController {

//    private User u1;
//
//    @Autowired
//    @Qualifier("user1")
//    public void setU1(User u1) {
//        this.u1 = u1;
//    }

    //第一种注入（查询）方式：属性注入
//    @Autowired
//    private LoginService loginService;

//    //第二种注入的方式：通过 Set 的方式注入
//    private LoginService loginService;
//    @Autowired
//    public void setLoginService(LoginService loginService) {
//        this.loginService = loginService;
//    }

    //第三种注入的方式：构造函数注入
    private LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void sayHi() {
        //参数效验
        loginService.sayHi();
    }

    @Bean
    public User user1() {
        User user = new User();
        user.setName("悟空");
        user.setPassword("妖怪哪里跑");
        return user;
    }

    @Bean
    public User user2() {
        User user = new User();
        user.setName("八戒");
        user.setPassword("背媳妇回家");
        return user;
    }

}
