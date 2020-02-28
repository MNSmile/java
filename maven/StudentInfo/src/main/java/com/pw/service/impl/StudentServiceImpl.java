package com.pw.service.impl;

import com.pw.dao.StudentDao;
import com.pw.dao.impl.StudentDaoImpl;
import com.pw.pojo.Student;
import com.pw.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao stuDao = new StudentDaoImpl();

    @Override
    public List<Student> queryAll() throws Exception {
        return stuDao.queryAll();
    }

    @Override
    public int delStudent(int id) throws Exception {
        return stuDao.delStudentById(id);
    }
}
