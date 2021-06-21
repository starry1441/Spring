package org.example.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -06 -21
 * Time: 9:28
 */

@Setter
@Getter
@ToString
public class User {
    private String name;
    private String password;
}
