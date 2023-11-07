package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name= "accountServlet", value = "/account")
public class AccountServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("account.jsp").forward(req,resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // nhan du lieu tu form
        // System.out.println(req.getParameter("userName"));
        // System.out.println(req.getParameter("email"));
        String userName = req.getParameter("userName");
        req.setAttribute("userName",userName);
        req.getRequestDispatcher("thanh.jsp").forward(req,resp);
    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
