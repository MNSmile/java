package com.pw.dao.impl;

import com.pw.dao.MessageDao;
import com.pw.pojo.Message;
import com.pw.util.DruidUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import javax.management.Query;
import java.sql.SQLException;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    @Override
    public List<Message> queryMessageByLoginUser(Long loginId, int start, Integer pageSize) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,sendid,title,msgcontent,state,receiveid,msg_create_date from messages where receiveid=? order by msg_create_date desc limit ?,?";
        return qr.query(sql, new BeanListHandler<>(Message.class), loginId, start, pageSize);
    }

    @Override
    public Long queryMsgCount(int loginid) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select count(*) from messages where receiveid=?";
        return qr.query(sql, new ScalarHandler<>(), loginid);
    }

    @Override
    public int deleteMsgById(String msgId) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "delete from messages where id=?";
        return qr.update(sql, msgId);
    }

    @Override
    public int saveMessage(Message msg) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "insert into messages values (null,?,?,?,?,?,?)";
        return qr.update(sql, msg.getSendid(), msg.getTitle(), msg.getMsgcontent(), msg.getState(), msg.getReceiveid(), msg.getMsg_create_date());
    }

    @Override
    public Message queryMessageById(int msgId) throws SQLException {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql = "select id,sendid,title,msgcontent,state,receiveid,msg_create_date from messages where id=?";
        return qr.query(sql, new BeanHandler<>(Message.class),msgId);
    }

    @Override
    public int updateMessage(Message msg) throws Exception {
        QueryRunner qr = new QueryRunner(DruidUtils.getDs());
        String sql ="update messages set state=? where id=?";
        return qr.update(sql,msg.getState(),msg.getId());
    }
}
