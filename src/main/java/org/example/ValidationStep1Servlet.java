package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/validation/step1")
public class ValidationStep1Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println(req.getParameterMap().keySet());
        String naam = req.getParameter("naam");
        String email = req.getParameter("e-mail");



        if(naam == null || naam.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "naam is required");
        }
        if(email == null || email.isEmpty()) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "email is required");
        }

        req.getSession().setAttribute("naam", naam);
        req.getSession().setAttribute("email", email);
    }
}
