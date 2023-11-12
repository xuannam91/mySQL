package com.example.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    // khai báo thuộc tính driver kết nối với mysql
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";

    // khai báo url kết  nối
     private static final String URL = "jdbc:mysql://localhost:3306/demo_mvc_jdbc";

     // khai bao user
    private static final String USER = "nam";

    // khai bao mat khau
    private static final String PASSWORD = "123456";

    // phương thức getConnection (lấy về kết nối)
    public static Connection getConnection(){
        Connection connection = null;
        try {
            // khai báo class cho driver
            Class.forName(DRIVER_JDBC);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection){
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(ConnectionDB.getConnection());
    }
}
