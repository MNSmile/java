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
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/lr.do")
public class LoginRegisterServlet extends HttpServlet {
    private UserService userService = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.设置请求编码格式
        req.setCharacterEncoding("utf-8");
        //2.设置响应编码格式
        resp.setContentType("text/html;charset=utf-8");
        resp.setCharacterEncoding("utf-8");
        //3.获取请求信息
        String param = req.getParameter("param");
        System.out.println(param);
        PrintWriter out = resp.getWriter();
        //4.处理请求信息
        if("login".equals(param)){
            String name = req.getParameter("name");
            String pwd = req.getParameter("pwd");
            String code = req.getParameter("code");  //用户填写的验证码
            String vcode = (String) req.getSession().getAttribute("code"); //生成的验证码
            req.getSession().removeAttribute("code");//删除先前生成的验证码,因为验证码只能用一次
            System.out.println("vcode="+vcode);
            System.out.println("code="+code);
            if(vcode == null || !vcode.equals(code)){
                //把错误信息放到request域中
                //req.setAttribute("info", "验证码出错");
                out.write("验证码出错"); //转发任务交给index.jsp中ajax
                //req.getRequestDispatcher("/index.jsp").forward(req, resp);
                return;
            } else{
                //5.响应处理结果
                User user = new User(name,pwd);
                //5.1调用业务层（service层）
                try {
                    user = userService.login(user);
                    System.out.println("LoginRegisterServlet"+user);
                    //登录成功
                    if(user!=null) {
                        req.getSession().setAttribute("user",user);
                        out.write("success");
                        //转发任务交给了index.jsp中的 $post的访问成功后的回调方法
                        //req.getRequestDispatcher("/msg.do?param=queryAllMsgs&pageSize=5&pageNo=1").forward(req,resp);
                    } else {
                        out.write("用户名或密码错误");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if("register".equals(param)){
            if(req.getAttribute("flag")!="true"){ //防止某些傻逼在出错之后还点击注册
                req.removeAttribute("flag");
                //req.getRequestDispatcher("/register.jsp").forward(req,resp);
                return;
            } else {
                String name = req.getParameter("name");
                String pwd = req.getParameter("pwd");
                String email = req.getParameter("email");
                User user = new User(name,pwd,email);
                try {
                    boolean flag = userService.register(user);
                    System.out.println("LoginRegisterService:flag="+flag);
                    if(flag){
                        out.write("注册成功");
                        resp.sendRedirect("/msg/index.jsp");
                    } else {
                        out.write("注册失败");
                        //req.getRequestDispatcher("/register.jsp").forward(req,resp);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if("queryByUser".equals(param)) {
            String name = req.getParameter("name");
            try {
                boolean flag = userService.queryUserByName(name);
                req.setAttribute("flag",flag);
                out.write(flag+"");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
}
