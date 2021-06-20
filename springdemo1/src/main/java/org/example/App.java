package org.example;

import org.example.controller.LoginController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -20
 * Time: 21:10
 */
public class App {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        LoginController loginController = (LoginController) context.getBean("loginController");
//        System.out.println(loginController);
        LoginController loginController = context.getBean(LoginController.class);
        System.out.println(loginController);
        //关闭容器
        ((ClassPathXmlApplicationContext)context).close();
    }

}

