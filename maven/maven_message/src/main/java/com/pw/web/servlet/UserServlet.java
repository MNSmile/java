package com.pw.web.servlet;

import com.pw.pojo.User;
import com.pw.service.UserService;
import com.pw.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@WebServlet("/user.do")
public class UserServlet extends HttpServlet {
    UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        //获取请求信息
        String param = req.getParameter("param");
        //处理请求信息
        if(param.equals("queryAllUsers")){
            String sendid = req.getParameter("sendid"); //?????????????????????????????????????????疑问
            if(sendid !=null) {
                req.setAttribute("sendid",sendid);
            }

            try {
                List<User> uList = userService.getAllUsers(); //得到所以用户信息，方便在发送信息中选择发送对象
                req.setAttribute("uList",uList); //把用户信息保存在request中
                req.getRequestDispatcher("/newMsg.jsp").forward(req,resp);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (param.equals("logout")){
            //响应处理结果
            req.getSession().invalidate(); //先销毁session
            // req.getContextPath() 得到虚拟地址
            resp.sendRedirect(req.getContextPath()+"/index.jsp");
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
