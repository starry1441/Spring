package com.example.usermanager.controller;

import com.example.usermanager.mapper.UserMapper;
import com.example.usermanager.model.UserInfo;
import com.example.usermanager.tools.AppFinal;
import com.example.usermanager.tools.ResponseBody;
import com.example.usermanager.tools.SessionUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -25
 * Time: 11:39
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserMapper userMapper;

    //登录方法
    @RequestMapping("/login")
    public ResponseBody<UserInfo> login(@RequestParam String username,
                                        @RequestParam String password,
                                        HttpServletRequest request) {
        UserInfo userInfo = userMapper.login(username,password);
        int status = -1;
        String message = "用户名或密码错误！";
        if (userInfo != null && userInfo.getId() > 0) {
            //登陆成功
            status = 0;
            message = "";
            //将用户对象存储到session中
            HttpSession session = request.getSession();
            session.setAttribute(AppFinal.USERINFO_SESSION_KEY,userInfo);
        }
        return new ResponseBody<>(status,message,userInfo);
    }

    //查询当前登录用户的权限
    @RequestMapping("/checkadmin")
    public ResponseBody<Integer> checkAdmin(HttpServletRequest request) {
        int data = 0;
        HttpSession session = request.getSession(false);
        UserInfo userInfo = null;
        if (session != null &&
                (userInfo = (UserInfo) session.getAttribute(AppFinal.USERINFO_SESSION_KEY)) != null) {
            data = userInfo.getIsadmin();
        }
        return new ResponseBody<>(0,"",data);
    }

    //添加用户方法
    @RequestMapping("/add")
    public ResponseBody<Integer> add(UserInfo userInfo,
                                     HttpServletRequest request) {
        int status = 0;
        String message = "";
        int data = 0;
        //安全效验
        UserInfo user = SessionUtil.getUserBySession(request);
        if (user == null) {
            //未登录
            status = -1;
            message = "当前用户未登录，请先登录";
        }else if (userInfo.getIsadmin() == 1) { //要添加超级管理员
            if (user.getIsadmin() == 0) {
                status = -2;
                message = "当前登录用户权限不足，不能操作";
            }else if (user.getIsadmin() == 1) {
                //当前登录用户为超级管理员
                data = userMapper.add(userInfo);
                status = 0;
            }
        }else if (userInfo.getIsadmin() == 0){
            data = userMapper.add(userInfo);
        }
        return new ResponseBody<>(status,message,data);
    }

    //获取用户详情
    @RequestMapping("/getuser")
    public ResponseBody<UserInfo> getUser(@RequestParam int id,
                                          HttpServletRequest request) {
        int status = -1;
        String message = "未知错误";
        UserInfo userInfo = userMapper.getUser(id);
        if (userInfo != null) {
            //权限效验
            UserInfo user = SessionUtil.getUserBySession(request);
            if (user == null) {
                status = -3;
                message = "非登录状态";
            }else if (user.getId() == userInfo.getId()) {
                status = 0;
            }else {
                status = -2;
                message = "非本账号修改页面，不能查看";
            }
        }
        return new ResponseBody<>(status,message,userInfo);
    }

    @RequestMapping("/update")
    public ResponseBody<Integer> update(UserInfo userInfo,
                                        HttpServletRequest request) {
        int status = -1;
        String message = "";
        int data = 0;
        //需要进行权限效验
        UserInfo user = SessionUtil.getUserBySession(request);
        if (user == null) {
            status = -2;
            message = "非登录状态";
        }else if (user.getId() == userInfo.getId()) {
            status = 0;
        }else {
            status = -3;
            message = "非本账号修改页面，不能修改";
        }
        data = userMapper.update(userInfo);
        return new ResponseBody<>(status,message,data);
    }

    //获取人员列表
    @RequestMapping("/getlist")
    public ResponseBody<List<UserInfo>> getlist(String name, String address, String email,
                                                        int cpage, int psize, HttpServletRequest request) {
        // 1.权限效验
        UserInfo user = SessionUtil.getUserBySession(request);
        if (user == null) {
            // 未登录
            return new ResponseBody<>(-1, "当前用户未登录", null);
        }
        int isadmin = user.getIsadmin();

        //2.处理前端的查询参数
        name = name.trim();
        address = address.trim();
        email = email.trim();

        //3.构建分页查询字段 limit skipCount,psize 查询当前页面的列表信息
        //跳过的字段：skipCount
        int skipCount = (cpage-1)*psize;
        //查询一页的信息
        List<UserInfo> list = userMapper.getListByPage(name,address,email,isadmin,skipCount,psize);
        return new ResponseBody<>(0,"",list);
    }

}
