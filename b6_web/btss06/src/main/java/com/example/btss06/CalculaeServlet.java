package com.example.btss06;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CalculaeServlet", value = "/calculaeservlet")
public class CalculaeServlet extends HttpServlet {
    @Override
    public void init() {

    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("form.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String description = req.getParameter("description");
        double prices  = Double.parseDouble(req.getParameter("price"));
        double percents  = Double.parseDouble(req.getParameter("percent"));
        double discountAmount = prices * percents * 0.01;
        double afterprice = prices - discountAmount;

        req.setAttribute("description", description);
        req.setAttribute("discountAmount", discountAmount);
        req.setAttribute("afterprice", afterprice);

        req.getRequestDispatcher("ketqua.jsp").forward(req,resp);

    }

    @Override
    public void destroy() {
        super.destroy();
    }
}