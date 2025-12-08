package org.example.controller.validators;

import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ValidationStep2ServletTest {

    private ValidationStep2Servlet servlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private StringWriter responseWriter;
    private PrintWriter printWriter;
    private Gson gson;

    @BeforeEach
    void setUp() throws Exception {
        servlet = new ValidationStep2Servlet();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        session = mock(HttpSession.class);

        when(request.getSession()).thenReturn(session);

        responseWriter = new StringWriter();
        printWriter = new PrintWriter(responseWriter);
        when(response.getWriter()).thenReturn(printWriter);

        gson = new Gson();
    }

    @Test
    void testMissingPostcode() throws Exception {
        when(request.getParameter("postcode")).thenReturn("");
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode is verplicht", responseMap.get("message"));
        verify(session, never()).setAttribute(anyString(), anyString());
    }

    @Test
    void testNullPostcode() throws Exception {
        when(request.getParameter("postcode")).thenReturn(null);
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode is verplicht", responseMap.get("message"));
    }

    @Test
    void testMissingHuisnummer() throws Exception {
        when(request.getParameter("postcode")).thenReturn("1234AB");
        when(request.getParameter("huisnummer")).thenReturn("");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Huisnummer is verplicht", responseMap.get("message"));
        verify(session, never()).setAttribute(anyString(), anyString());
    }

    @Test
    void testNullHuisnummer() throws Exception {
        when(request.getParameter("postcode")).thenReturn("1234AB");
        when(request.getParameter("huisnummer")).thenReturn(null);

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Huisnummer is verplicht", responseMap.get("message"));
    }

    @Test
    void testInvalidHuisnummerFormat() throws Exception {
        when(request.getParameter("postcode")).thenReturn("1234AB");
        when(request.getParameter("huisnummer")).thenReturn("abc");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Huisnummer moet in het formaat 999 zijn", responseMap.get("message"));
        verify(session, never()).setAttribute(anyString(), anyString());
    }

    @Test
    void testInvalidPostcodeFormat() throws Exception {
        when(request.getParameter("postcode")).thenReturn("12AB");
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode moet in het formaat 1234AB zijn", responseMap.get("message"));
        verify(session, never()).setAttribute(anyString(), anyString());
    }

    @Test
    void testInvalidPostcodeLowercase() throws Exception {
        when(request.getParameter("postcode")).thenReturn("1234ab");
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode moet in het formaat 1234AB zijn", responseMap.get("message"));
    }

    @Test
    void testInvalidPostcodeTooManyDigits() throws Exception {
        when(request.getParameter("postcode")).thenReturn("12345AB");
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode moet in het formaat 1234AB zijn", responseMap.get("message"));
    }

    @Test
    void testValidInput() throws Exception {
        when(request.getParameter("postcode")).thenReturn("1234AB");
        when(request.getParameter("huisnummer")).thenReturn("123");

        servlet.doPost(request, response);

        verify(response).setContentType("application/json");
        verify(response).setCharacterEncoding("UTF-8");

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertTrue((Boolean) responseMap.get("success"));
        assertEquals("Stap 2 succesvol verzonden", responseMap.get("message"));

        verify(session).setAttribute("postcode", "1234AB");
        verify(session).setAttribute("huisnummer", "123");
    }

    @Test
    void testValidInputSingleDigitHuisnummer() throws Exception {
        when(request.getParameter("postcode")).thenReturn("9999ZZ");
        when(request.getParameter("huisnummer")).thenReturn("5");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertTrue((Boolean) responseMap.get("success"));
        assertEquals("Stap 2 succesvol verzonden", responseMap.get("message"));

        verify(session).setAttribute("postcode", "9999ZZ");
        verify(session).setAttribute("huisnummer", "5");
    }

    @Test
    void testValidInputMultiDigitHuisnummer() throws Exception {
        when(request.getParameter("postcode")).thenReturn("9999ZZ");
        when(request.getParameter("huisnummer")).thenReturn("56");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertTrue((Boolean) responseMap.get("success"));
        assertEquals("Stap 2 succesvol verzonden", responseMap.get("message"));

        verify(session).setAttribute("postcode", "9999ZZ");
        verify(session).setAttribute("huisnummer", "56");
    }

    @Test
    void testWhitespaceHandling() throws Exception {
        when(request.getParameter("postcode")).thenReturn("  ");
        when(request.getParameter("huisnummer")).thenReturn("  ");

        servlet.doPost(request, response);

        printWriter.flush();
        String jsonResponse = responseWriter.toString();
        Map<String, Object> responseMap = gson.fromJson(jsonResponse, Map.class);

        assertFalse((Boolean) responseMap.get("success"));
        assertEquals("Postcode is verplicht", responseMap.get("message"));
    }
}
