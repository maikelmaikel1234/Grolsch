package org.example.controller.validators;

import com.google.gson.Gson;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/validation/step1")
public class ValidationStep1Servlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Map<String, Object> response = new HashMap<>();
        
        String naam = req.getParameter("naam");
        String email = req.getParameter("e-mail");

        // Validation
        if(naam == null || naam.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Naam is verplicht");
            out.print(gson.toJson(response));
            return;
        }


        if(email == null || email.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "E-mail is verplicht");
            out.print(gson.toJson(response));

            return;
        }

        if (!isValidEmail(email)) {
            response.put("success", false);
            response.put("message", "Ongeldig e-mail formaat");
            out.print(gson.toJson(response));
            return;
        }

        // Save to session
        req.getSession().setAttribute("naam", naam);
        req.getSession().setAttribute("email", email);

        // Success response
        response.put("success", true);
        response.put("message", "Stap 1 succesvol verzonden");
        out.print(gson.toJson(response));
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email.matches(emailRegex);
    }
}
