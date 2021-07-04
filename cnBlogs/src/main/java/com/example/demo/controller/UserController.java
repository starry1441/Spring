package com.example.demo.controller;

import com.example.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;

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
        HashMap<String, Object> map = new HashMap<>();
        int status = 0;
        String msg = "未知错误";
        String data = "登录失败";
        if (user != null &&
                user.getUsername().equals("root") &&
                user.getPassword().equalsIgnoreCase("root")) {
            //将登录信息存储到Session中
            HttpSession session = request.getSession();
            session.setAttribute("userinfo", user);
            status = 1;
            msg = "用户名密码正确";
            data = "登录成功";
        }
        map.put("status", status);
        map.put("msg", msg);
        map.put("data", data);
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

    @RequestMapping("/reg")
    @ResponseBody
    public Object regin(String username, String password,
                        @RequestPart MultipartFile file) throws IOException {
        //非空效验

        //1.动态获取当亲项目的路径
        String path = ClassUtils.getDefaultClassLoader().getResource("static").getPath();
        path += "/upload/";
        //2.文件名（全局唯一id【UUID】）+ 文件的原始类型
        String fileType = file.getOriginalFilename();   //img.png
        fileType = fileType.substring(fileType.lastIndexOf("."));
        //文件名
        String fileName = UUID.randomUUID().toString() + fileType;
        //将文件保存到服务器
        file.transferTo(new File(path + fileName));

        return "/login.html";
    }

//    @RequestMapping("/test")
//    @ResponseBody
//    public void test() {
//        String a = null;
//        System.out.println(a.hashCode());
//    }

}
