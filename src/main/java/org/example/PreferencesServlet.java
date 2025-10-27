package org.example;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/preferences")
public class PreferencesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") == null) {
            response.sendRedirect("/login");
            return;
        }

        Cookie language = getCookie(request, "language", "nl") ;
        Cookie theme = getCookie(request, "theme", "light");

        response.addCookie(language);
        response.addCookie(theme);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>Register</h1>");
        out.println("<form method='POST'>");
        out.println("<select name='theme' id='theme'>");
        out.println("<option value='light'" + (theme.getValue().equals("light") ? " selected" : "") + ">Light</option>");
        out.println("<option value='dark'" + (theme.getValue().equals("dark") ? " selected" : "") + ">Dark</option>");
        out.println("</select>");
        out.println("<select name='language' id='language'>");
        out.println("<option value='nl'" + (language.getValue().equals("nl") ? " selected" : "") + ">Nl</option>");
        out.println("<option value='en'" + (language.getValue().equals("en") ? " selected" : "") + ">En</option>");
        out.println("</select>");
        out.println("<button type='submit' value='Submit'>Submit</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        String language = request.getParameter("language");
        String theme = request.getParameter("theme");
        
        response.addCookie(createCookie("language", language));
        response.addCookie(createCookie("theme", theme));

        response.sendRedirect("/survey/step1");
    }

    private Cookie getCookie(HttpServletRequest request, String cookieName, String defaultValue)
    {
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookieName.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }

        Cookie cookie = createCookie(cookieName, defaultValue);
        return cookie;

    }

    private static Cookie createCookie(String cookieName, String defaultValue) {
        Cookie cookie = new Cookie(cookieName, defaultValue);
        cookie.setMaxAge(3000);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        return cookie;
    }
}
