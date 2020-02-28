package com.pw.web.servlet;

import com.pw.pojo.Message;
import com.pw.pojo.PageEntity;
import com.pw.pojo.User;
import com.pw.service.MessageService;
import com.pw.service.impl.MessageServiceImpl;
import com.pw.util.StringUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/msg.do")
public class MessageServlet extends HttpServlet {
    MessageService msgService = new MessageServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //2.设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");

        String param = req.getParameter("param");

        if("queryAllMsgs".equals(param)){
            //从session中取出当前登录的User对象
            User user = (User) req.getSession().getAttribute("user");
            //声明页面管理变量
            PageEntity pageEntity = new PageEntity();
            Integer pageNo = Integer.parseInt(req.getParameter("pageNo"));//当前页
            Integer pageSize = Integer.parseInt(req.getParameter("pageSize"));//每页显示的记录数
            try {
                List<Message> list = msgService.queryMessageByLoginUser(user.getId(), (pageNo-1)*pageSize, pageSize);
                pageEntity.setList(list);
                pageEntity.setPageNo(pageNo);
                pageEntity.setPageSize(pageSize);
                Long totalRecords = msgService.queryMsgCount((int) (user.getId().longValue()));
                pageEntity.setTotalRecords(totalRecords);
                req.setAttribute("pageEntity", pageEntity);
                System.out.println("所有短信记录数："+pageEntity.getTotalRecords());
                req.getRequestDispatcher("/main.jsp").forward(req, resp);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if("delMsg".equals(param)) {
            String msgId = req.getParameter("id"); //该消息id
            System.out.println("MessageServlet:该消息id"+msgId);
            try {
                int res = msgService.deleteMsgById(msgId); //影响行数
                System.out.println("MessageServlet:影响行数"+res);
                if(res > 0){
                    req.getRequestDispatcher("/msg.do?param=queryAllMsgs").forward(req,resp);
                } else {
                    resp.getWriter().write("alert('删除失败！')");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if("sendMsg".equals(param)){
            Long sendid = ((User)req.getSession().getAttribute("user")).getId();
            Integer receiveId = Integer.parseInt(req.getParameter("toUser"));
            System.out.println("MessageServlet: sendid="+sendid);
            System.out.println("MessageServlet: receiveId="+receiveId);
            String title = StringUtil.replaceStr(req.getParameter("title"));
            String msgContent = StringUtil.replaceStr(req.getParameter("content"));
            String msg_create_date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            int state = 1;
            try {
                Message msg = new Message(sendid, title, msgContent, state, receiveId, msg_create_date);
                int res = msgService.sendMessage(msg);
                System.out.println("MessageServlet: 影响行数="+res);
                if(res>0){
                    resp.getWriter().write("alert('发送成功！')");
                    req.getRequestDispatcher("/user.do?param=queryAllUsers").forward(req, resp);
                } else{
                    resp.getWriter().write("alert('发送失败！')");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if("showMsgById".equals(param)){
            Integer msgId = Integer.parseInt(req.getParameter("id"));
            System.out.println("MessageServlet-->该消息id为："+msgId);
            try {
                Message msg = msgService.queryMessageById(msgId);
                System.out.println("MessageServlet-->该消息："+msg);
                req.setAttribute("msg", msg);
                req.getRequestDispatcher("/readMsg.jsp").forward(req,resp);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
