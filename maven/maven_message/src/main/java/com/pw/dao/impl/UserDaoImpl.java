package com.pw.dao.impl;

import com.pw.dao.UserDao;
import com.pw.pojo.User;
import com.pw.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //根据用户包含信息查询数据库中是否有这个用户
    @Override
    public User queryUserByUser(User user) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,name,pwd,email from users where name=? and pwd=?";
        return queryRunner.query(sql, new BeanHandler<>(User.class),user.getName(),user.getPwd());
    }

    @Override
    public boolean saveUser(User user) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into users values(null,?,?,?)";
        return qr.update(sql,user.getName(),user.getPwd(),user.getEmail())>0?true:false;
    }

    @Override
    public List<User> queryAllUsers() throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,name,pwd,email from users";
        return qr.query(sql,new BeanListHandler<>(User.class)); //注意是 BeanListHandler<>()
    }

    @Override
    public User queryUserBySendid(Long sendid) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,name,pwd,email from users where id=?";
        return qr.query(sql,new BeanHandler<>(User.class),sendid);
    }

    @Override
    public boolean queryUserByName(String name) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,name,pwd,email from users where name=?";
        return qr.query(sql, new BeanHandler<>(User.class), name) == null ? false : true;
    }
}

