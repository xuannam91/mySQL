package com.example.demo;

public class Student {
    private String studentId;
    private String fullName;
    private Integer age;
    private Boolean sex;

    public Student() {
    }

    public Student(String studentId, String fullName, Integer age, Boolean sex) {
        this.studentId = studentId;
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getSex() {
        return sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }
}

