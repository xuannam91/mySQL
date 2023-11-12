package com.ra.controller;

import com.ra.model.entity.Student;
import com.ra.model.service.StudentService;

import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/studentservlet")
public class StudentServlet extends HttpServlet {
    private StudentService  studentService ;
    @Override
    public void init() throws ServletException {
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
                Student student = studentService.findId(id);
                //// hien thi ra form or  view jsp
                request.setAttribute("student", student);
                request.getRequestDispatcher("views/student-edit.jsp").forward(request,response);
                break;
            case "delete":
                Integer idDelete = Integer.parseInt(request.getParameter("id"));
                studentService.delete(idDelete);
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
            studentService.save(new Student(studentName,age,sex));
            resp.sendRedirect("/studentservlet");
        }
        else if("update".equals(action)){
            int id = Integer.parseInt(req.getParameter("studentCode"));
            Student student = new Student(id,studentName,age,sex);
            studentService.update(student,id);
                resp.sendRedirect("/studentservlet");
        }


    }

    public void showListStudent (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Student> list = studentService.findAll();
        request.setAttribute("list", list);
        request.getRequestDispatcher("views/student.jsp").forward(request,response);
    }



}