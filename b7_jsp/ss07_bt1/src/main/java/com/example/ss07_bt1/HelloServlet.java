package com.example.ss07_bt1;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {

    List<Customers> customersList = new ArrayList<>();
    public void init() {
        customersList.add(new Customers(1,"xuan nam",30));
        customersList.add(new Customers(2,"Minh",13));
        customersList.add(new Customers(3,"quang HUY",32));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.setAttribute("list", customersList);
        request.getRequestDispatcher("customer.jsp").forward(request,response);
    }

    public void destroy() {
    }

}