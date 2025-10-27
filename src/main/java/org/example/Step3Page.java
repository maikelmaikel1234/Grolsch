package org.example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Step3Page extends Page {
    public Step3Page(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }
    @Override
    public void render() throws IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") == null) {
            response.sendRedirect("/login");
            return;
        }

        PrintWriter out = getWriter();
        String language = getLanguage();
        String theme = getTheme();
        String username = getUsername();

        // Gebruik language en theme om je HTML te genereren
        out.println("<html>");
        out.println("<head>");
        out.println("<style>");
        if ("dark".equals(theme)) {
            out.println("body { background: #222; color: #eee; }");
        } else {
            out.println("body { background: #fff; color: #222; }");
        }
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");
        if ("en".equals(language)) {
            out.println("<h1>Welcome " + username + "!</h1>");
            out.println("<p>Step 1: Personal information</p>");
        } else {
            out.println("<h1>Welkom " + username + "!</h1>");
            out.println("<p>Stap 1: Persoonlijke informatie</p>");
        }
        out.println("<p>Naam: "+ session.getAttribute("naam")+"</p>");
        out.println("<p>Email: "+ session.getAttribute("e-mail")+"</p>");
        out.println("<p>Postcode: "+ session.getAttribute("postcode")+"</p>");
        out.println("<p>Huisnummer: "+ session.getAttribute("huisnummer")+"</p>");
        out.println("</body></html>");
    }
}
