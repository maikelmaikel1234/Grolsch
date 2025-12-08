package org.example.controller.validators;

import com.google.gson.Gson;
import org.example.controller.validators.ValidationStep1Servlet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ValidationStep1ServletTest {

    private ValidationStep1Servlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private StringWriter responseWriter;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ValidationStep1Servlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        responseWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
    }

    @org.junit.Test
    public void testMissingName() throws Exception {
        when(request.getParameter("naam")).thenReturn("");
        when(request.getParameter("e-mail")).thenReturn("test@example.com");

        servlet.doPost(request, response);

        String json = responseWriter.toString();
        assertTrue(json.contains("\"success\":false"));
        assertTrue(json.contains("Naam is verplicht"));
    }

    @Test
    void testMissingEmail() throws Exception {
        when(request.getParameter("naam")).thenReturn("John");
        when(request.getParameter("e-mail")).thenReturn("");

        servlet.doPost(request, response);

        String json = responseWriter.toString();
        assertTrue(json.contains("\"success\":false"));
        assertTrue(json.contains("E-mail is verplicht"));
    }

    @Test
    void testInvalidEmail() throws Exception {
        when(request.getParameter("naam")).thenReturn("John");
        when(request.getParameter("e-mail")).thenReturn("invalid-email");

        servlet.doPost(request, response);

        String json = responseWriter.toString();
        assertTrue(json.contains("\"success\":false"));
        assertTrue(json.contains("Ongeldig e-mail formaat"));
    }

    @Test
    void testValidInput() throws Exception {
        when(request.getParameter("naam")).thenReturn("John");
        when(request.getParameter("e-mail")).thenReturn("john@example.com");

        servlet.doPost(request, response);

        String json = responseWriter.toString();
        assertTrue(json.contains("\"success\":true"));
        assertTrue(json.contains("Stap 1 succesvol verzonden"));
        verify(session).setAttribute("naam", "John");
        verify(session).setAttribute("email", "john@example.com");
    }
}
