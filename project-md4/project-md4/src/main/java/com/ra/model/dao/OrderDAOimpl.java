package com.ra.model.dao;

import com.ra.dto.response.ResponseUserLoginDTO;
import com.ra.model.entity.Order;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OrderDAOimpl implements OrderDAO{
@Autowired
private UserDAO userDAO;
    @Override
    public Boolean createOrder(Order order) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL PRO_InsertOrder(?,?,?,?,?,?,?)");
            callableStatement.setInt(1,order.getUser().getUserId());
            callableStatement.setDouble(2,order.getTotalAmount());
            callableStatement.setString(3,order.getName());
            callableStatement.setString(4,order.getEmail());
            callableStatement.setString(5,order.getPhone());
            callableStatement.setString(6,order.getAddress());
            callableStatement.setDouble(7,order.getTotalPrice());

            int rs = callableStatement.executeUpdate();
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
    public List<Order> allOrder(){
        Connection connection = null;
        connection = ConnectionDB.openConnection();
        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callableStatement = connection.prepareCall("CALL GetAllOrders()");
            ResultSet rs = callableStatement.executeQuery();
            while (rs.next()){
                Order order = new Order();
                order.setOrderId(rs.getInt("orderId"));

                User user = userDAO.findById(rs.getInt("userId"));
                order.setUser(user);

                order.setTotalAmount(rs.getDouble("totalAmount"));
                order.setOrderDate(rs.getDate("orderDate"));
                order.setName(rs.getString("name"));
                order.setEmail(rs.getString("email"));
                order.setPhone(rs.getString("phone"));
                order.setAddress(rs.getString("address"));
                order.setTotalPrice(rs.getDouble("totalPrice"));

                orderList.add(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }

        return orderList;
    }

}
