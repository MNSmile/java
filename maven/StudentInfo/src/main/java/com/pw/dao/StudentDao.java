package com.pw.dao;

import com.pw.pojo.Student;

import java.util.List;

public interface StudentDao {
    public List<Student> queryAll() throws Exception; //查询所有学生信息
    public int delStudentById(int id) throws Exception; //删除学生信息
}
