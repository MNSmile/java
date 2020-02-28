package com.pw.service;

import com.pw.pojo.Student;

import java.util.List;

public interface StudentService {
    public List<Student> queryAll() throws Exception; //查询学生信息
    public int delStudent(int id) throws Exception;  //根据id删除学生信息
}
