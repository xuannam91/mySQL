package com.example.model.service;

import com.example.model.dao.StudentDAOimp;
import com.example.model.entity.Student;

import java.util.List;

public class StudentService implements IGenericService<Student,Integer>{
    private final StudentDAOimp studentDAOimp = new StudentDAOimp();


    @Override
    public List<Student> findAll() {
        return studentDAOimp.findAll();
    }

    @Override
    public Boolean save(Student student) {
        return studentDAOimp.save(student);
    }

    @Override
    public Student find(Integer integer) {
        return studentDAOimp.find(integer);
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
