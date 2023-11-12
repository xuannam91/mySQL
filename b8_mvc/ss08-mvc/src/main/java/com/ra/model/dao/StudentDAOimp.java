package com.ra.model.dao;

import com.ra.model.entity.Student;
import com.ra.util.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOimp implements IGenericDAO<Student, Integer>{
    @Override
    public List<Student> findAll() {
        Connection connection = null;
        List<Student> list = new ArrayList<>();


        try {
            //mo ket noi
            connection = ConnectionDB.getConnection();
           //chuan bi cau lenh truy van
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM student" );
            //thuc thi
            ResultSet rs = pstm.executeQuery();
            
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
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }

    @Override
    public Boolean save(Student student) {
        Connection connection = null;

        try {
            // mở kết nối
            connection = ConnectionDB.getConnection();
            // xây dựng câu truy vấn
            String sql = "INSERT INTO student(studentName,age,sex) VALUES(?,?,?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,student.getStudentName());
            pstm.setInt(2,student.getAge());
            pstm.setBoolean(3,student.isSex());

            // thuc thi
            int check = pstm. executeUpdate();
            if(check > 0){
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    @Override
    public Student findId(Integer integer) {
        Connection connection = null;
        Student student = new Student();
        try {
            connection = ConnectionDB.getConnection();
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM student WHERE studentCode = ? ");
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
        } finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }


        return student;
    }

    @Override
    public Boolean update(Student student, Integer integer) {
        Connection connection = null;
        try {
            // mở kết nối
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
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return false;
    }

    @Override
    
    public void delete(Integer integer) {
        Connection connection = null;

        try {
            // mở kết nối
            connection = ConnectionDB.getConnection();
            //chuan bi cau lenh truy van
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM student WHERE studentCode = ?");
            pstm.setInt(1,integer);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                ConnectionDB.closeConnection(connection);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
