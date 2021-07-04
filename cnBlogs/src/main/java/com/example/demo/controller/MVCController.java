package com.example.demo.controller;

import com.example.demo.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -27
 * Time: 11:30
 */

//@Controller //spring初始化此类
//@ResponseBody
//@RestController   //可以取代前连个注解
@RequestMapping("/mvc")
@Slf4j
public class MVCController {

    @Autowired
    private ObjectMapper objectMapper;

    @ResponseBody
    @RequestMapping("/index8")
    public User getIndex8() {
        User user = new User();
        user.setUsername("Java");
        user.setPassword("123");
        return user;
    }

    @RequestMapping("/index7")
    @ResponseBody
    public String getIndex7() throws JsonProcessingException {
        User user = new User();
        user.setUsername("Java");
        user.setPassword("123");
        //将对象转换成 json 字符串
        String result = objectMapper.writeValueAsString(user);
        log.error(result);
        return result;
    }

    @RequestMapping("/index")
    public String getIndex() {
        log.error("我是index.html");
        return "redirect:/index.html";
    }

    @RequestMapping("/index2")
    public String getIndex2() {
        log.error("我是请求转发");
        return "forward:/index.html";
    }

    //实现301跳转
    @RequestMapping("/index3")
    public String getIndex3(HttpServletResponse response, HttpServletRequest request) {
        response.setStatus(301);
        response.setHeader("Location", "http://localhost:8080/index.html");
        return null;
    }

    @GetMapping("/index4")
    public String getIndex4() {
        log.error("我是index4");
        return "/index.html";
    }

    @PostMapping("/index5")
    public String getIndex5() {
        log.error("我是index5");
        return "/index.html";
    }

    @RequestMapping("/index6")
    @ResponseBody   //返回类型为自定义类型，而非视图
    public String getIndex6() {
        return "/index.html";
    }

}
