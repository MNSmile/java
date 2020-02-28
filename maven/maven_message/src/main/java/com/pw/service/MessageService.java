package com.pw.service;

import com.pw.pojo.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageService {
    /**
     *获取登录用户收到的所有短消息
     * @param loginId 用户id
     * @param start 开始
     * @param pageSize 每页显示的记录数
     * @return
     */
    List<Message> queryMessageByLoginUser(Long loginId, int start, Integer pageSize) throws SQLException;

    /**
     * 查询登录用户收到的短消息个数
     * @param loginId
     * @return
     */
    Long queryMsgCount(int loginId) throws Exception;

    /**
     * 删除一条消息
     * @param msgId 消息id
     * @return
     */
    int deleteMsgById(String msgId) throws SQLException;

    /**
     * 发送消息
     * @param msg
     * @return
     */
    int sendMessage(Message msg) throws SQLException;

    /**
     * 查询一条消息
     * @param msgId 消息id
     * @return
     */
    Message queryMessageById(Integer msgId) throws SQLException, Exception;
}
