package com.ra.dto.response;

public class ResponseUserLoginDTO {
    private Integer id;
    private String userName;
    private String email;
    private Byte role = 0;

    public ResponseUserLoginDTO() {
    }

    public ResponseUserLoginDTO(Integer id, String userName, String email, Byte role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Byte getRole() {
        return role;
    }

    public void setRole(Byte role) {
        this.role = role;
    }
}
