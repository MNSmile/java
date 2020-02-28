package com.pw.test;

import com.pw.pojo.Student;
import com.pw.service.StudentService;
import com.pw.service.impl.StudentServiceImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StudentTest {
    @Test
    public void testStudent() throws Exception{
        StudentService studentService = new StudentServiceImpl();
        List<Student> students = studentService.queryAll();
        System.out.println(Arrays.toString(students.toArray()));
        //studentService.delStudent(3);
    }
}
