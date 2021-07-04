package org.example;

import org.example.config.MyApplication;
import org.example.controller.LoginController;
import org.example.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.Introspector;

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

        LoginController loginController = context.getBean(LoginController.class);
        loginController.sayHi();

//        MyApplication application = context.getBean(MyApplication.class);
//        System.out.println(application);

//        User user = (User)context.getBean("user1");
//        System.out.println(user);
//
//        LoginController loginController = context.getBean(LoginController.class);
//
//        User user2 = context.getBean("user2",User.class);
//        System.out.println(user2);
//        LoginController loginController = (LoginController) context.getBean("loginController");
//        System.out.println(loginController);
//        LoginController loginController = context.getBean(LoginController.class);
//        System.out.println(loginController);
//        System.out.println(Introspector.decapitalize("LoginController"));
        //关闭容器
        ((ClassPathXmlApplicationContext) context).close();
    }

}

