package com.ra.model.dao;

import com.ra.dto.request.UserLoginDTO;
import com.ra.model.entity.User;
import com.ra.util.ConnectionDB;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class USerDAOimpl implements UserDAO{
    @Override
    public Boolean register(User user) {
        Connection connection = null;
        connection = ConnectionDB.openConnection();

        // Tạo salt ngẫu nhiên

        String salt = BCrypt.gensalt(12);

        // Kết hợp salt với mật khẩu và sau đó mã hóa mật khẩu
        String hashedPassword = BCrypt.hashpw(user.getPassword(), salt);
        try {
            PreparedStatement pstm = connection.prepareStatement("INSERT INTO users(userName,email,password) VALUES (?,?,?)");
            pstm.setString(1,user.getUserName());
            pstm.setString(2,user.getEmail());
            pstm.setString(3,hashedPassword);
            int rs = pstm.executeUpdate();
            if(rs > 0 ){
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
    public User login(UserLoginDTO user) {
        Connection connection = null;
        User user1=new User();
        connection = ConnectionDB.openConnection();

        try {
            PreparedStatement pstm = connection.prepareStatement("SELECT * FROM users WHERE email = ?");
            pstm.setString(1, user.getEmail());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    String hashedPasswordFromDB = rs.getString("password");
                    String enteredPassword = user.getPassword();
                    if (BCrypt.checkpw(enteredPassword, hashedPasswordFromDB)) {
                        user1.setUserName(rs.getString("userName"));
                        user1.setEmail(rs.getString("email"));
                        user1.setRole(rs.getByte("role"));
                        return user1;
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            ConnectionDB.closeConnection(connection);
        }
        return null;
    }


}
