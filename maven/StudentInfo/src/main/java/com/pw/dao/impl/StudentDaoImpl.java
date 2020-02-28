package com.pw.dao.impl;

import com.pw.dao.StudentDao;
import com.pw.pojo.Student;
import com.pw.util.Cp30Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.util.List;

public class StudentDaoImpl implements StudentDao {

    @Override
    public List<Student> queryAll() throws Exception{
        QueryRunner qr = new QueryRunner(Cp30Utils.getDs());
        String sql = "select id,name,age,sex,score from stu";
        return qr.query(sql, new BeanListHandler<>(Student.class));
    }

    @Override
    public int delStudentById(int id) throws Exception {
        QueryRunner qr = new QueryRunner(Cp30Utils.getDs());
        String sql = "delete from stu where id=?";
        return qr.update(sql,id);
    }
}
