package com.example.demo.model;

import lombok.Data;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -27
 * Time: 15:14
 */

@Data
public class User {

    private int id;
    private String username;
    private String password;
    private String photo;
    private List<ArticleInfo> alist;

}
