package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {
        // Show registration form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        System.out.println("doGet");


        out.println("<html><body>");
        out.println("<h1>Register</h1>");
        out.println("<form method='POST'>");
        out.println("<label for= 'naam'>Naam: </label>");
        out.println("<input type = 'text' name = 'naam'/><br>");
        out.println("<label for= 'email'>Email: </label>");
        out.println("<input type = 'text' name = 'email'/><br>");
        out.println("<input type = 'text' name = 'step' value='1'/><br>");
        out.println("<button type='submit' value='Submit'>Submit</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        // TODO: Process registration
        // 1. Get parameters
        // 2. Validate (not null, not empty)
        // 3. Display confirmation or error

        System.out.println("doPost");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        request.getParameter("email");

        if(request.getParameter("step").equals("1"))
        {

            out.println("<html><body>");
            out.println("<h1>Register</h1>");
            out.println("<form method='POST'>");
            request.getParameterMap().forEach((key, value) -> {
                if(key.equals("step"))
                    out.println("<input type = 'text' name = 'step' value='2'/><br>");
                else
                  out.println("<input type = 'hidden' name = '%s' value = '%s'/>".formatted(key, value[0]));
            });
            out.println("<label for= 'postcode'>Postcode: </label>");
            out.println("<input type = 'text' name = 'postcode'/><br>");
            out.println("<label for= 'huisnummer'>Huisnummer: </label>");
            out.println("<input type = 'number' name = 'huisnummer'/><br>");
            out.println("<button type='submit' value='Submit'>Submit</button>");
            out.println("</form>");
            out.println("</body></html>");
        }

        if(request.getParameter("step").equals("2"))
        {

            out.println("<html><body>");
            out.println("<h1>Register</h1>");
            out.println("<form method='POST'>");
            request.getParameterMap().forEach((key, value) -> {
                if(key.equals("step"))
                    out.println("<input type = 'text' name = 'step' value='3'/><br>");
                else
                    out.println("<input type = 'hidden' name = '%s' value = '%s'/>".formatted(key, value[0]));
            });
            out.println("<label for= 'voorkeuren'>Voorkeuren: </label>");
            out.println("<input type = 'text' name = 'voorkeuren'/><br>");
            out.println("<button type='submit' value='Submit'>Submit</button>");
            out.println("</form>");
            out.println("</body></html>");
        }

        if(request.getParameter("step").equals("3"))
        {
            out.println("<html><body>");
            out.println("<h1>Register</h1>");
            out.println("<form method='POST'>");
            request.getParameterMap().forEach((key, value) -> {
                    out.println("<p> %s: %s </p>".formatted(key, value[0]));
            });
            out.println("</form>");
            out.println("</body></html>");
        }
    }
}
