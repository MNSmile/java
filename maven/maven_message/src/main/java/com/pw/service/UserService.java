package com.pw.service;

import com.pw.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService {
    /**
     * 用户登录
     * @param user
     * @return 用户
     */
    public  User login(User user) throws SQLException;

    /**
     * 用户注册
     * @param user
     * @return
     */
    boolean register(User user) throws SQLException;

    /**
     * 获得所有用户信息
     * @return
     */
    List<User> getAllUsers() throws SQLException;

    /**
     * 根据名字查询用户
     * @param name
     * @return
     */
    boolean queryUserByName(String name) throws SQLException;
}
