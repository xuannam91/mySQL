package com.ra.model.dao;

import com.ra.model.entity.Category;
import com.ra.model.entity.Product;
import com.ra.util.ConnectionDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Repository

public class ProductDAOImpl implements ProductDAO{
    @Autowired
    private CategoryDAO categoryDAO;
    @Override
    public List<Product> findAll() {
        Connection connection = null;
        List<Product> list = new ArrayList<>();
        connection = ConnectionDB.openConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product");
            ResultSet rs = pstm.executeQuery();
            while (rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
                list.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return list;
    }

    @Override
    public Boolean create(Product product) {
        Connection connection = ConnectionDB.openConnection();
        try {
        String sql = "INSERT INTO product ( name_product,price,category_id,image) VALUES (?, ?, ?, ?)";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,product.getNameProduct());
            pstm.setString(2, String.valueOf(product.getPrice()));
            pstm.setInt(3,product.getCategory().getCategoryId());
            pstm.setString(4, product.getImage());
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
    public Boolean update(Product product, Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            String sql = "UPDATE product SET name_product = ?, price = ?, category_id = ?, image = ? WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1,product.getNameProduct());
            pstm.setString(2, String.valueOf(product.getPrice()));
            pstm.setInt(3,product.getCategory().getCategoryId());
            pstm.setString(4, product.getImage());
            pstm.setInt(5,integer);
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
    public Product findById(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        Product product = new Product();
        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM product WHERE id = ?");
            pstm.setInt(1,integer);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()){
                product.setId(rs.getInt("id"));
                product.setNameProduct(rs.getString("name_product"));
                product.setPrice(rs.getDouble("price"));
                Category category = categoryDAO.findById(rs.getInt("category_id"));
                product.setCategory(category);
                product.setImage(rs.getString("image"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return product;
    }

    @Override
    public void delete(Integer integer) {
        Connection connection = ConnectionDB.openConnection();
        try {
            PreparedStatement pstm = connection.prepareStatement("DELETE FROM product WHERE id = ?");
            pstm.setInt(1, integer);
            pstm.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
    }
}
