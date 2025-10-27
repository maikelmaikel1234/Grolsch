package org.example;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class Page {

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;
    public Page(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        this.session = request.getSession();
    }
    // Elke page moet zichzelf kunnen renderen
    public abstract void render() throws IOException;
    // Helper methods die elke page kan gebruiken
    protected void setSessionData(String key, Object value) {
        session.setAttribute(key, value);
    }
    protected Object getSessionData(String key) {
        return session.getAttribute(key);
    }
    protected String getUsername() {
        return (String) session.getAttribute("username");
    }
    protected String getLanguage() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("language".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "nl"; // default
    }
    protected String getTheme() {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("theme".equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return "light"; // default
    }
    protected PrintWriter getWriter() throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        return response.getWriter();
    }
}
