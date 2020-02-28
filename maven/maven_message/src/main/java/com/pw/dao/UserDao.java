package com.pw.dao;

import com.pw.pojo.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao {
    /**
     * 根据用户包含信息查询数据库中是否有这个用户
     * @param user
     * @return 用户
     */
    User queryUserByUser(User user) throws SQLException;

    /**
     *添加用户
     * @param user
     * @return
     */
    boolean saveUser(User user) throws SQLException;

    /**
     * 获得所有用户信息
     * @return
     */
    List<User> queryAllUsers() throws SQLException;

    /**
     * 根据用户id查询用户
     * @param sendid
     * @return
     */
    User queryUserBySendid(Long sendid) throws SQLException;

    /**
     * 根据姓名查询用户
     * @return
     */
    boolean queryUserByName(String name) throws SQLException;
}
