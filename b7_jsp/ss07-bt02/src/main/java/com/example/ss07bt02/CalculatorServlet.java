package com.example.ss07bt02;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CalculatorServlet", value = "/calculatorservlet")
public class CalculatorServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        double first = Double.parseDouble(req.getParameter("first"));
        double second = Double.parseDouble(req.getParameter("second"));
        String operator = req.getParameter("operator");

        String result = null;

        switch (operator) {
            case "cong":
                result = first + " + " + second + " = " + (first + second);
                break;
            case "tru":
                result = first + " - " + second + " = " + (first - second);
                break;
            case "nhan":
                result = first + " * " + second + " = " + (first * second);
                break;
            case "chia":
                result = first + " / " + second + " = " + (first / second);
                System.out.println(result);
                if(result.equals("10.0 / 0.0 = Infinity")) {
                    result = first + " / " + second + " = 0.0";
                }
                break;
            default:
                break;
        }

        req.setAttribute("result", result);
        req.getRequestDispatcher("/calculator.jsp").forward(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}