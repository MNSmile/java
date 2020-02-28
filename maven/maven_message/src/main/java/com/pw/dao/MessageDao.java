package com.pw.dao;

import com.pw.pojo.Message;

import java.sql.SQLException;
import java.util.List;

public interface MessageDao {
    /**
     * 获取登录用户收到的所有短消息
     * @param loginId
     * @param start
     * @param pageSize
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
     * @param msgId
     * @return
     */
    int deleteMsgById(String msgId) throws SQLException;

    /**
     * 添加一条消息
     * @param msg
     * @return
     */
    int saveMessage(Message msg) throws SQLException;

    /**
     * 查询一条消息
     * @return
     */
    Message queryMessageById(int msgId) throws SQLException;

    /**
     * 更新消息
     * @param msg
     */
    int updateMessage(Message msg) throws Exception;
}
