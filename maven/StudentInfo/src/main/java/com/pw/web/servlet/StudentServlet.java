package com.pw.web.servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.pw.pojo.Student;
import com.pw.service.StudentService;
import com.pw.service.impl.StudentServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/stu.do")
public class StudentServlet extends HttpServlet {
    StudentService studentService = new StudentServiceImpl();
    
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置响应编码格式
        resp.setContentType("application/json; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //创建响应输出对象
        PrintWriter out = resp.getWriter();
        //获取请求参数
        String param = req.getParameter("param");
        //根据参数的不同实现不同功能
        if (param.equals("queryAll")){ //查询学生信息功能
            try {
                //调用业务层
                List<Student> stuList = studentService.queryAll();
                //将返回结果转为json格式
                String str = JSON.toJSONString(stuList, SerializerFeature.PrettyFormat);
                //System.out.println("StudentServlet");
                //System.out.println(str);
                out.write(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if(param.equals("del")){ //删除学生信息功能
            //获取相应学生信息 id
            String id = req.getParameter("id");
            //创建map集合用于收集结果，最终转为json数据
            Map<String,String> map = new HashMap<>();
            try {
                //调用业务层
                int res = studentService.delStudent(Integer.parseInt(id)); //影响行数
                //System.out.println("res="+res);
                if(res>0){
                    map.put("info","success");
                } else {
                    map.put("info","failure");
                }
            } catch (Exception e) {
                map.put("info","failure");
                e.printStackTrace();
            }
            String jsonStr = JSON.toJSONString(map, SerializerFeature.PrettyFormat);
            out.write(jsonStr);
        }
    }
}
