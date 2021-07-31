package com.example.demo.mapper;

import com.example.demo.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -18
 * Time: 13:47
 */

@SpringBootTest
@Transactional //保证方法可以正常执行，并且不会存储到数据库中
class UserMapperTest {

    @Resource
    private UserMapper userMapper;

    @Test
    void addUser() {
        User user = new User();
        user.setUsername("孙猴子");
        user.setPassword("123");
        user.setPhoto("abc.png");
        int result = userMapper.addUser(user); // 返回的永远是修改的行数
        System.out.println(result);
        System.out.println("-----------------------id:" + user.getId());
    }

    @Test
    void getUserById() {
        User user = userMapper.getUserById(5);
        System.out.println(user);
    }

    @Test
    void getUserByNameAndPassword() {
        User user = userMapper.getUserByNameAndPassword("1","1");
        System.out.println(user);
    }

    @Test
    void getAll() {
        List<User> list = userMapper.getAll();
        list.forEach(System.out::println);
    }

    @Test
    void delById() {
        int result = userMapper.delById(3);
        System.out.println(result);
    }

    @Test
    void upUser() {
        int result = userMapper.upUser(7,"孙悟空");
        System.out.println(result);
    }

    @Test
    void getList() {
        List<User> list = userMapper.getList("desc");
        list.forEach(System.out::println);
    }

    @Test
    void getListByName() {
//        String username = "孙";
        String username = "%' or username='%";
        //手动过滤sql注入
//        username = username.replace("'","").
//                replace("or","").
//                replace("and","");
        List<User> list = userMapper.getListByName(username);
        list.forEach(System.out::println);
    }

    @Test
    void getListByName2() {
        String username = "孙";
//        String username = "%' or username='%";
        //手动过滤sql注入
        username = username.replace("'","").
                replace("or","").
                replace("and","");
        List<User> list = userMapper.getListByName(username);
        list.forEach(System.out::println);
    }

    @Test
    void getFullUser() {
        User user = userMapper.getFullUser(5);
        System.out.println(user);
    }
}