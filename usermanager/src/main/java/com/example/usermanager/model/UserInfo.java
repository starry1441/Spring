package com.example.usermanager.model;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -24
 * Time: 16:03
 */

@Data
public class UserInfo {

    private int id;
    private String name;
    private String username;
    private String password;
    private String sex;
    private int age;
    private String address;
    private String qq;
    private String email;
    private int isadmin;

}
