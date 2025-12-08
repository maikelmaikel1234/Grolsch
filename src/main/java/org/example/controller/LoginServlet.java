package org.example.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    // Hardcoded users voor demo
    private static final Map<String, String> USERS = Map.of(
            "admin", "admin456",
            "user", "password",
            "jan", "geheim"
    );

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException {

        // TODO: Check if already logged in
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            response.sendRedirect("/preferences");
            return;
        }

        // TODO: Toon login form
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String error = request.getParameter("error");

        out.println("<html><body>");
        out.println("<h1>Login</h1>");

        if (error != null) {
            out.println("<p style='color:red'>Invalid username or password!</p>");
        }

        out.println("<form method='POST'>");
        out.println("  <label>Username: <input name='username' required /></label><br/>");
        out.println("  <label>Password: <input name='password' type='password' required /></label><br/>");
        out.println("  <button type='submit'>Login</button>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (USERS.containsKey(username) &&
                USERS.get(username).equals(password)) {

            HttpSession session = request.getSession(true);
            session.setAttribute("username", username);
            session.setAttribute("loginTime", System.currentTimeMillis());

            response.sendRedirect("/preferences");
        } else {
            response.sendRedirect("/login?error=true");
        }
    }
}