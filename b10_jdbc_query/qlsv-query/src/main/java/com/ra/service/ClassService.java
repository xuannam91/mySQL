package com.ra.service;

import com.ra.model.dao.ClassRoomDAO;
import com.ra.model.entity.ClassRoom;

import java.util.List;

public class ClassService implements IGenericService<ClassRoom, Integer>{
    ClassRoomDAO classRoomDAO= new ClassRoomDAO();
    @Override
    public List<ClassRoom> findAll() {
       return classRoomDAO.findAll();
    }

    @Override
    public Boolean create(ClassRoom classRoom) {
        return null;
    }

    @Override
    public Boolean update(ClassRoom classRoom, Integer integer) {
        return null;
    }

    @Override
    public ClassRoom findId(Integer integer) {
        return null;
    }

    @Override
    public void delete(Integer integer) {

    }
}
