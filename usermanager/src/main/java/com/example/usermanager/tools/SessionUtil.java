package com.example.usermanager.tools;

import com.example.usermanager.model.UserInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -25
 * Time: 15:03
 */
public class SessionUtil {

    //获取session中的用户对象
    public static UserInfo getUserBySession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        UserInfo userInfo = null;
        if (session != null &&
                (userInfo = (UserInfo) session.getAttribute(AppFinal.USERINFO_SESSION_KEY)) != null) {
        }
        return userInfo;
    }

}
