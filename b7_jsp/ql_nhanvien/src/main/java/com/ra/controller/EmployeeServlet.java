package com.ra.controller;

import com.ra.model.Employee;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "EmployeeServlet", value = "/employeeservlet")
public class EmployeeServlet extends HttpServlet {
    private final List<Employee> list = new ArrayList<>();
    @Override
    public void init() {
        list.add(new Employee("NV01","Tien Bip", 15, 1000));
        list.add(new Employee("NV02","Hoang Hai", 20, 500));
        list.add(new Employee("NV03","Tam tac",  25, 2000));
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null){
            getList(request,response);
            return;
        }
        switch (action){
            case "edit":
                String id = request.getParameter("id");
                Employee employee = findById(id);
                request.setAttribute("employee", employee);
                request.getRequestDispatcher("view/edit.jsp").forward(request,response);
                break;
            case "delete":
                String iddelete = request.getParameter("id");
                Employee employee1 = findById(iddelete);
                list.remove(employee1);
                getList(request,response);
                break;
            default:
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String employeeCode = req.getParameter("employeeCode");
        String fullName = req.getParameter("fullName");
        Integer age = Integer.valueOf(req.getParameter("age"));
        Double salary = Double.valueOf(req.getParameter("salary"));

        String action = req.getParameter("action");

        if(action == null){
            list.add(new Employee(employeeCode,fullName,age,salary));
        }else{

            Employee employee = findById(employeeCode);
            employee.setFullName(fullName);
            employee.setAge(age);
            employee.setSalary(salary);

        }
        getList(req,resp);
    }

    // tạo phương thư để lấy về danh sách
    public void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException{
        //        nhận method Get gui le tu client
        req.setAttribute("list",list);
        // nhận dữ liệu req res sang employee.jsp;
        req.getRequestDispatcher("view/employee.jsp").forward(req,resp);
    }

    public Employee findById(String id){
        for (Employee employee: list) {
            if(employee.getEmployeeCode().equals(id))
                return employee;
        }
        return null;
    }

}