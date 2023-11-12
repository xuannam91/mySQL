package com.example.model.dao;

import com.example.model.entity.Student;
import com.example.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOimp implements IGenericDAO<Student,Integer>{
    @Override
    public List<Student> findAll() {

        Connection connection = null;
        List<Student> list = new ArrayList<>();
        try {
            // mở kết nối.
            connection = ConnectionDB.getConnection();
            // chuẩn bị câu lệnh truy vấn
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM student");
            // thực thi
            ResultSet rs = pstm.executeQuery();
            // chuyển đổi thành dữ liệu java
            while (rs.next()){
                Student student = new Student();
                student.setStudentCode(rs.getInt("studentCode"));
                student.setStudentName(rs.getString("studentName"));
                student.setAge(rs.getInt("age"));
                student.setSex(rs.getBoolean("sex"));
                list.add(student);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean save(Student student) {
        Connection connection = null;

        try {
            // mở kết nôí
            connection = ConnectionDB.getConnection();
            // xây dựng câu lẹnh thêm mới
            String sql = "INSERT INTO student(studentName,age,sex) VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,student.getStudentName());
            pstm.setInt(2,student.getAge());
            pstm.setBoolean(3,student.isSex());

            // thuc thi
           int check =  pstm.executeUpdate();
           if(check > 0){
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
    public Student find(Integer integer) {
        Connection connection = null;
        Student student = new Student();
        try {
            // mở kết nôí
            connection = ConnectionDB.getConnection();
            // xây dựng câu lẹnh thêm mới
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM student WHERE studentCode = ?");
            pstm.setInt(1,integer);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                student.setStudentCode(rs.getInt("studentCode"));
                student.setStudentName(rs.getString("studentName"));
                student.setAge(rs.getInt("age"));
                student.setSex(rs.getBoolean("sex"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return student;
    }

    @Override
    public Boolean update(Student student, Integer integer) {
        Connection connection = null;

        try {
            // mở kết nôí
            connection = ConnectionDB.getConnection();
            String sql = "UPDATE student SET studentName = ?, age = ?, sex = ? WHERE studentCode = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setInt(4, integer);
            pstm.setString(1, student.getStudentName());
            pstm.setInt(2,student.getAge());
            pstm.setBoolean(3,student.isSex());

            int check = pstm.executeUpdate();
            if(check > 0){
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
    public void delete(Integer integer) {
        Connection connection = null;

        try {
            // mở kết nôí
            connection = ConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM student WHERE studentCode = ?");
            pstm.setInt(1, integer);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }

    }
}
