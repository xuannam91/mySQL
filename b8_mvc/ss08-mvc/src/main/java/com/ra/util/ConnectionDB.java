package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    // Khai báo thuọc tính driver kết nối mySQL
    private static final String DRIVER_JDBC = "com.mysql.cj.jdbc.Driver";

   // KHai bao url ket noi
   private  static final String URL = "jdbc:mysql://localhost:3306/demo_mvc_jdbc";


   // Khai bao thuoc tinh user
    private static final String USER = "nam";
    private static final String PASSWORD = "123456";

    // Phương thức get connection (lay ve ket noi)
    public static Connection getConnection(){
        Connection connection = null;

        try {
            // khai báo class cho driver
            Class.forName(DRIVER_JDBC);
            // Thực hiện mở ket nối
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }catch (SQLException | ClassNotFoundException e){
            throw new RuntimeException(e);
        }
        return connection;
    }


    // phương thức đóng kết nối
    public static void closeConnection(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
        }
    }


    public static void main(String[] args) {
        System.out.println(ConnectionDB.getConnection());
    }
}
