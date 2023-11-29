package com.ra.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class UserRegisterDTO {
    @NotEmpty(message = "Tên không được rỗng")
    private String userName;
    @NotEmpty(message = "không được rỗng")
    @Email(message = "Sai dinh dạng email")
    private String email;
    @Size(min = 3,max = 16, message = "Sai cmnr")
    private String password;


//    @PhoneNumberConstraint(message = "Sai ")
//@NotEmpty(message = "không được rỗng")
//    private String phone;


    private Byte role = 0;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String userName, String email, String password, Byte role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }
}
