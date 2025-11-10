package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class HomePage extends Page{

    public HomePage(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void render() throws IOException {
        HttpSession session = request.getSession(false);
//        if (session != null && session.getAttribute("username") == null) {
//            response.sendRedirect("/login");
//            return;
//        }

        PrintWriter out = getWriter();
        String language = getLanguage();
        String theme = getTheme();
        String username = getUsername();

        // Gebruik language en theme om je HTML te genereren
        out.println("<html>");
        out.println("<head>");

//        out.println("<style>");
//        if ("dark".equals(theme)) {
//            out.println("body { background: #222; color: #eee; }");
//        } else {
//            out.println("body { background: #fff; color: #222; }");
//        }
//        out.println("</style>");
        out.println("<link rel='stylesheet' href=\"styles.css\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div id='feedback'></div>");
        out.println("<div class='loading' id='loading'>");
        out.println("<div class='spinner'></div>");
        out.println("<p>Loading...</p>");
        out.println("</div>");

        out.println("<div id='stap1'>");
        if ("en".equals(language)) {
            out.println("<h1>Welcome " + username + "!</h1>");
            out.println("<p>Step 1: Personal information</p>");
        } else {
            out.println("<h1>Welkom " + username + "!</h1>");
            out.println("<p>Stap 1: Persoonlijke informatie</p>");
        }
        out.println("<form id='step1Form'>");
        out.println("<label for= 'naam'>Naam: </label>");
        out.println("<input id='naam' type = 'text' name = 'naam'/><br>");
        out.println("<label for= 'e-mail'>e-mail: </label>");
        out.println("<input id='email' type = 'text' name = 'e-mail'/><br>");
        out.println("<button id='next' type='submit' value='Submit'>Submit</button>");
        out.println("</form>");
        out.println("</div>");

        out.println("<div id='stap2' class='invisible'>");
        out.println("<form id='step2Form'>");
        out.println("<label for= 'postcode'>Postcode: </label>");
        out.println("<input id = 'postcode' type = 'text' name = 'postcode'/><br>");
        out.println("<label for= 'huisnummer'>Huisnummer: </label>");
        out.println("<input id='huisnummer' type = 'number' name = 'huisnummer'/><br>");
        out.println("<button type='submit' value='Submit'>Submit</button>");
        out.println("</form>");
        out.println("</div>");

        out.println("<div id='stap3' class='invisible'>");
        out.println("<table>");
        out.println("<tr><td><label for='naam_result'>Naam:</label></td><td><p id='naam_result'></p></td></tr>");
        out.println("<tr><td><label for='email_result'>E-mail:</label></td><td><p id='email_result'></p></td></tr>");
        out.println("<tr><td><label for='postcode_result'>Postcode:</label></td><td><p id='postcode_result'></p></td></tr>");
        out.println("<tr><td><label for='huisnummer_result'>Huisnummer:</label></td><td><p id='huisnummer_result'></p></td></tr>");
        out.println("</table>");
        out.println("</div>");


        out.println("<script src=\"script.js\"></script>");

        out.println("</body></html>");
    }
}

