package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.util.ConnectionDB;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO{
    @Override
    public List<Category> findAll() {
        Connection connection = null;
        List<Category> list = new ArrayList<>();
        connection = ConnectionDB.openConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM category");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Category category = new Category();
                category.setCategoryId(rs.getInt("id"));
                category.setCategoryName(rs.getString("name"));
                category.setStatus(rs.getBoolean("status"));
                list.add(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;

    }

    @Override
    public Boolean create(Category category) {
        Connection connection = ConnectionDB.openConnection();
        String sql = "INSERT INTO category (name,status) VALUES (?,?)";
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,category.getCategoryName());
            pstm.setBoolean(2,category.getStatus());
            int rs = pstm.executeUpdate();
            if(rs > 0){
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
    public Boolean update(Category category, Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "UPDATE category SET name = ?, status = ? WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,category.getCategoryName());
            pstm.setBoolean(2,category.getStatus());
            pstm.setInt(3,integer);
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
    public Category findById(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        Category category = new Category();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
            pstm.setInt(1,integer);
           ResultSet rs = pstm.executeQuery();

           while (rs.next()){
               category.setCategoryId(rs.getInt("id"));
               category.setCategoryName(rs.getString("name"));
               category.setStatus(rs.getBoolean("status"));
           }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return category;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM category WHERE id = ?");
            pstm.setInt(1, integer);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
