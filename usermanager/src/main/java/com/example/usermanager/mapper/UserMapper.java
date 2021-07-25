package com.example.usermanager.mapper;

import com.example.usermanager.model.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: starry
 * Date: 2021 -07 -25
 * Time: 11:45
 */

@Mapper
public interface UserMapper {

    public UserInfo login(String username, String password);

    public int add(UserInfo userInfo);

    public UserInfo getUser(int id);

    public int update(UserInfo userInfo);

    public List<UserInfo> getListByPage(String name, String address, String email, int isadmin, int skipCount, int psize);
}
