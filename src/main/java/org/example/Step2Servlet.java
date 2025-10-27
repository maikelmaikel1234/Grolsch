package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/survey/step2")
public class Step2Servlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        Step2Page page = new Step2Page(request, response);
        page.render();
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        // Verwerk form data
        String huisnummer = request.getParameter("huisnummer");
        String postcode = request.getParameter("postcode");

        // Sla op in session
        HttpSession session = request.getSession();
        session.setAttribute("huisnummer", huisnummer);
        session.setAttribute("postcode", postcode);

        // Ga naar volgende stap
        response.sendRedirect("/survey/step3");
    }
}
