package org.example;

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

@WebServlet("/validation/step2")
public class ValidationStep2Servlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        Map<String, Object> response = new HashMap<>();

        String postcode = req.getParameter("postcode");
        String huisnummer = req.getParameter("huisnummer");

        // Validation
        if(postcode == null || postcode.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Postcode is verplicht");
            out.print(gson.toJson(response));
            return;
        }

        if(huisnummer == null || huisnummer.trim().isEmpty()) {
            response.put("success", false);
            response.put("message", "Huisnummer is verplicht");
            out.print(gson.toJson(response));
            return;
        }

        // Basic postcode validation (Dutch format: 1234AB)
        if (!postcode.matches("[0-9]{4}[A-Z]{2}")) {
            response.put("success", false);
            response.put("message", "Postcode moet in het formaat 1234AB zijn");
            out.print(gson.toJson(response));
            return;
        }

        // Save to session
        req.getSession().setAttribute("postcode", postcode);
        req.getSession().setAttribute("huisnummer", huisnummer);

        // Success response
        response.put("success", true);
        response.put("message", "Stap 2 succesvol verzonden");
        out.print(gson.toJson(response));
    }
}
