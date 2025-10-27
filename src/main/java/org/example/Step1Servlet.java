package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/survey/step1")
public class Step1Servlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Step1Page page = new Step1Page(request, response);
        page.render();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Verwerk form data
        String naam = request.getParameter("naam");
        String e_mail = request.getParameter("e-mail");

        // Sla op in session
        HttpSession session = request.getSession();
        session.setAttribute("naam", naam);
        session.setAttribute("e-mail", e_mail);

        // Ga naar volgende stap
        response.sendRedirect("/survey/step2");
    }
}
