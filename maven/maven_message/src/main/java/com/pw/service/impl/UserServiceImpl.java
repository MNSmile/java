package com.pw.service.impl;

import com.pw.dao.UserDao;
import com.pw.dao.impl.UserDaoImpl;
import com.pw.pojo.User;
import com.pw.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    //声明变量
    private UserDao userDao = new UserDaoImpl();

    //用户登录
    @Override
    public User login(User user) throws SQLException {
        return userDao.queryUserByUser(user);
    }
    //用户注册
    @Override
    public boolean register(User user) throws SQLException {
        return userDao.saveUser(user);
    }
    //获取所有用户信息
    @Override
    public List<User> getAllUsers() throws SQLException {
        return userDao.queryAllUsers();
    }

    @Override
    public boolean queryUserByName(String name) throws SQLException {
        return userDao.queryUserByName(name);
    }
}
