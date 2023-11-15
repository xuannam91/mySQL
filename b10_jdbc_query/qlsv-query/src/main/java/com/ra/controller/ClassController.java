package com.ra.controller;

import com.ra.model.entity.ClassRoom;
import com.ra.service.ClassService;

import java.awt.*;
import java.io.*;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "ClassController", value = "/class-controller")
public class ClassController extends HttpServlet {

    private ClassService classService;
    @Override
    public void init() {
        classService = new ClassService();
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<ClassRoom> classRooms = classService.findAll();
        request.setAttribute("classRooms", classRooms);
        request.getRequestDispatcher("views/classroom.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

}