package com.ra.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    //
    private final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    private final static String URL = "jdbc:mysql://localhost:3306/ss13controller";

    private static final String USER = "nam";

    // khai bao mat khau
    private static final String PASSWORD = "123456";

        public static Connection openConnection(){
        Connection connection = null;
        try {
            // khai báo class cho driver
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL,USER,PASSWORD);
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
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

}
