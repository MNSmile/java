package com.pw.service.impl;

import com.pw.dao.MessageDao;
import com.pw.dao.UserDao;
import com.pw.dao.impl.MessageDaoImpl;
import com.pw.dao.impl.UserDaoImpl;
import com.pw.pojo.Message;
import com.pw.pojo.User;
import com.pw.service.MessageService;

import java.sql.SQLException;
import java.util.List;

public class MessageServiceImpl implements MessageService {
    MessageDao msgDao = new MessageDaoImpl();
    UserDao userDao = new UserDaoImpl();

    //获取登录用户收到的所有短消息
    @Override
    public List<Message> queryMessageByLoginUser(Long loginId, int start, Integer pageSize) throws SQLException {
        return msgDao.queryMessageByLoginUser(loginId,start,pageSize);
    }

    //查询登录用户收到的短消息个数
    @Override
    public Long queryMsgCount(int loginId) throws Exception {
        return msgDao.queryMsgCount(loginId);
    }
    //根据消息id删除一条消息
    @Override
    public int deleteMsgById(String msgId) throws SQLException {
        return msgDao.deleteMsgById(msgId);
    }
    //添加一条消息
    @Override
    public int sendMessage(Message msg) throws SQLException {
        return msgDao.saveMessage(msg);
    }
    //根据消息id查询一条消息，并添加message用户信息。
    @Override
    public Message queryMessageById(Integer msgId) throws Exception {
        Message msg = msgDao.queryMessageById(msgId);
        Long sendid = msg.getSendid();
        User sendUser = userDao.queryUserBySendid(sendid);
        msg.setSendUser(sendUser);
        msg.setState(0);
        //调用到层进行更新
        msgDao.updateMessage(msg);
        return msg;
    }
}
