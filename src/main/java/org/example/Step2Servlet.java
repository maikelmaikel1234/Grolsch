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

        // Validate input
        if (huisnummer == null || huisnummer.trim().isEmpty()) {
            // Redirect back to step2 with error
            response.sendRedirect("/survey/step2?error=Huisnummer is verplicht");
            return;
        }

        if (postcode == null || postcode.trim().isEmpty()) {
            // Redirect back to step2 with error
            response.sendRedirect("/survey/step2?error=Postcode is verplicht");
            return;
        }

        // Basic postcode validation (Dutch format: 1234AB)
        if (!postcode.matches("[0-9]{4}[A-Z]{2}")) {
            response.sendRedirect("/survey/step2?error=Postcode moet in het formaat 1234AB zijn");
            return;
        }

        try {
            // Validate that huisnummer is a valid number
            Integer.parseInt(huisnummer);
        } catch (NumberFormatException e) {
            response.sendRedirect("/survey/step2?error=Huisnummer moet een geldig nummer zijn");
            return;
        }

        // Sla op in session
        HttpSession session = request.getSession();
        session.setAttribute("huisnummer", huisnummer);
        session.setAttribute("postcode", postcode);

        // Ga naar volgende stap
        response.sendRedirect("/survey/step3");
    }
}
