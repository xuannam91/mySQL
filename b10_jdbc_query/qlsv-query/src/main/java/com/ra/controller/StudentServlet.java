package com.ra.controller;

import com.ra.dto.StudentDTO;
import com.ra.model.entity.ClassRoom;
import com.ra.service.ClassService;
import com.ra.service.StudentService;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "StudentServlet", value = "/studentservlet")
public class StudentServlet extends HttpServlet {
    private StudentService studentService = null;
    private ClassService classService = null;

    @Override
    public void init() {
        studentService = new StudentService();
        classService = new ClassService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            getAll(request, response);
        }
        List<ClassRoom> list = classService.findAll();
        if (action.equals("add")) {
            request.setAttribute("list", list);
            request.getRequestDispatcher("views/add-student.jsp").forward(request, response);
            return;
        }
        if (action.equals("edit")) {

            int studentId = Integer.parseInt(request.getParameter("id"));
            StudentDTO studentDTO = studentService.findId(studentId);
            request.setAttribute("student", studentDTO);
            request.setAttribute("list", list);
            request.getRequestDispatcher("views/edit-student.jsp").forward(request,response);
            return;
        }
        if(action.equals("delete")){
             int studentIdDelete = Integer.parseInt(request.getParameter("id"));
            studentService.delete(studentIdDelete);
            getAll(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        String name = req.getParameter("name");
        String dateStr = req.getParameter("birthday");
        Integer classId = Integer.parseInt(req.getParameter("classId"));


        // khởi tạo mẫu kiểu date theo format Date của sql
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date birthDay;
        try {
            // chuyển đổi String sang Date của java.util.Date
            birthDay = formatter.parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        if (action == null) {
            StudentDTO studentDTO = new StudentDTO();

            studentDTO.setName(name);
            // đổi từ java.util.Date sang java.sql.Date
            studentDTO.setBirthday(new java.sql.Date(birthDay.getTime()));
            studentDTO.setClassId(classId);

            studentService.create(studentDTO);
            getAll(req, resp);
            return;
        }

        if(action.equals("edit")){
            Integer studentId = Integer.parseInt(req.getParameter("studentId"));

            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setName(name);
            // đổi từ java.util.Date sang java.sql.Date
            studentDTO.setBirthday(new java.sql.Date(birthDay.getTime()));
            studentDTO.setClassId(classId);

            studentService.update(studentDTO,studentId);
            getAll(req, resp);
        }

    }

    public void getAll(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<StudentDTO> list = studentService.findAll();
        req.setAttribute("list", list);
        req.getRequestDispatcher("views/student.jsp").forward(req, resp);
    }

}