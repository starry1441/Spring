package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -27
 * Time: 10:16
 */

@RequestMapping("/user")
@Controller
@Slf4j
public class UserController {

    @RequestMapping("/login")
    @ResponseBody   //返回类型为数据类型，而非视图
    public Object getLogin(User user, HttpServletRequest request) {
        HashMap<String,Object> map = new HashMap<>();
        int status = 0;
        String msg = "未知错误";
        String data = "登录失败";
        if (user.getUsername().equals("root") && user.getPassword().equals("root")) {
            //将登录信息存储到Session中
            HttpSession session = request.getSession();
            session.setAttribute("userinfo",user);
            status = 1;
            msg = "用户名密码正确";
            data = "登录成功";
        }
        map.put("status",status);
        map.put("msg",msg);
        map.put("data",data);
        return map;
    }

//    //创建一个当前类的日志对象
//    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/index")
    @ResponseBody
    public String getIndex(String name) {
//        logger.error("我的日志信息，级别：error");
//        logger.warn("我的日志信息，级别：warn");
//        logger.info("我的日志信息，级别：info");
//        logger.debug("我的日志信息，级别：debug");
//        logger.trace("我的日志信息，级别：trace");
        log.error("我的日志信息，级别：error");
        log.warn("我的日志信息，级别：warn");
        log.info("我的日志信息，级别：info");
        log.debug("我的日志信息，级别：debug");
        log.trace("我的日志信息，级别：trace");
        return "你好，SpringBoot~";
    }

}
