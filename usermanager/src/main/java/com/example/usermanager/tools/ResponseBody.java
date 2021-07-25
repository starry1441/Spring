package com.example.usermanager.tools;

import lombok.Data;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -25
 * Time: 12:32
 */

@Data
public class ResponseBody<T> {

    private int status;
    private String message;
    private T data;
    public ResponseBody(int status,String message,T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}
