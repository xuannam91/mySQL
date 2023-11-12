package com.example.controller;

import com.example.model.entity.Student;
import com.example.model.service.StudentService;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/studentservlet")
public class StudentServlet extends HttpServlet {

    private StudentService studentService;
    @Override
    public void init() {
        studentService = new StudentService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if(action == null){
            showListStudent(request,response);
        }
        switch (action){
            case "edit":
                Integer id = Integer.parseInt(request.getParameter("id"));
                Student student = studentService.find(id);
                request.setAttribute("student", student);
                request.getRequestDispatcher("views/student-edit.jsp").forward(request,response);
                break;
            case "delete":
                Integer deleteId = Integer.parseInt(request.getParameter("id"));
                studentService.delete(deleteId);
                showListStudent(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String studentName = req.getParameter("fullName");
        int age = Integer.parseInt(req.getParameter("age"));
        boolean sex = req.getParameter("sex").equals("true");
        String action = req.getParameter("action");

        if("save".equals(action)){
            Student addStudent = new Student(studentName, age, sex);
            studentService.save(addStudent);
            showListStudent(req,resp);
        }
        if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("studentCode"));
            Student student = new Student(id,studentName,age,sex);
            studentService.update(student,id);
            resp.sendRedirect("/studentservlet");
        }


    }

    public void showListStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("views/student.jsp").forward(request,response);

    }

}