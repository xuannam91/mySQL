package com.ra.service;

import com.ra.dto.StudentDTO;
import com.ra.model.dao.StudentDAO;

import java.util.List;

public class StudentService implements IGenericService<StudentDTO,Integer>{
    private StudentDAO studentDAO = new StudentDAO();
    @Override
    public List<StudentDTO> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        return studentDAO.create(studentDTO);
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer integer) {
        return studentDAO.update(studentDTO,integer);
    }

    @Override
    public StudentDTO findId(Integer id) {
        return studentDAO.findId(id);
    }

    @Override
    public void delete(Integer integer) {
        studentDAO.delete(integer);
    }
}
