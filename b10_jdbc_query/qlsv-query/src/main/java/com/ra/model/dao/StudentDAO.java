package com.ra.model.dao;

import com.ra.dto.StudentDTO;
import com.ra.model.entity.Student;
import com.ra.util.ConnectionDB;

import javax.xml.transform.Result;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO implements IGenericDAO<StudentDTO, Integer>{

    @Override
    public List<StudentDTO> findAll() {
        Connection connection = null;
        List<StudentDTO> list = new ArrayList<>();
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_GETALLSTUDENT()}");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(rs.getInt("id"));
                studentDTO.setName(rs.getString("name"));
                studentDTO.setBirthday(rs.getDate("birthDay"));
                studentDTO.setClassName(rs.getString("class-name"));
                list.add(studentDTO);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(StudentDTO studentDTO) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_INSERTSTUDENT(?,?,?)}");
            callableStatement.setString(1,studentDTO.getName());
            callableStatement.setDate(2, studentDTO.getBirthday());
            callableStatement.setInt(3, studentDTO.getClassId());
            int check = callableStatement.executeUpdate();
            if(check > 0 ){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public Boolean update(StudentDTO studentDTO, Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CAll PROC_UPDATESTUDENTBYID(?,?,?,?)}");
            callableStatement.setInt(1,integer);
            callableStatement.setString(2,studentDTO.getName());
            callableStatement.setDate(3,studentDTO.getBirthday());
            callableStatement.setInt(4,studentDTO.getClassId());
            int check = callableStatement.executeUpdate();
            System.out.println(check);
            if (check > 0){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return false;
    }

    @Override
    public StudentDTO findId(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        StudentDTO studentDTO = new StudentDTO();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CAll FIND_ONE(?)}");
            callableStatement.setInt(1,integer);
           ResultSet rs = callableStatement.executeQuery();
           while (rs.next()){
               studentDTO.setId(rs.getInt("id"));
               studentDTO.setName(rs.getString("name"));
               studentDTO.setBirthday(rs.getDate("birthDay"));
               studentDTO.setClassId(rs.getInt("class_id"));
               studentDTO.setClassName(rs.getString("className"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return studentDTO;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("{CALL PROC_DELETESTUDENTBYID(?)}");
            callableStatement.setInt(1,integer);
            callableStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
