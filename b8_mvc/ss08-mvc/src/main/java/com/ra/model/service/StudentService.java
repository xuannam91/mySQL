package com.ra.model.service;

import com.ra.model.dao.StudentDAOimp;
import com.ra.model.entity.Student;

import java.util.List;

public class StudentService implements IGenericService<Student, Integer>{

    private final StudentDAOimp  studentDAOimp = new StudentDAOimp();
    @Override
    public List<Student> findAll() {
        return studentDAOimp.findAll();
    }

    @Override
    public Boolean save(Student student) {

        return studentDAOimp.save(student);
    }

    @Override
    public Student findId(Integer integer) {
        return studentDAOimp.findId(integer);
    }

    @Override
    public Boolean update(Student student, Integer integer) {
        return studentDAOimp.update(student,integer);
    }

    @Override
    public void delete(Integer integer) {
        studentDAOimp.delete(integer);
    }
}
