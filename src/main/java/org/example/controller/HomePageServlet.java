package org.example.controller;

import org.example.view.HomePage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ajaxHome")
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HomePage homePage = new HomePage(req, resp);
        homePage.render();
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        // Check if all required attributes exist in session
//        Object naamObj = session.getAttribute("naam");
//        Object emailObj = session.getAttribute("email");
//        Object postcodeObj = session.getAttribute("postcode");
//        Object huisnummerObj = session.getAttribute("huisnummer");
//
//        if (naamObj == null || emailObj == null || postcodeObj == null || huisnummerObj == null) {
//            // Handle missing session attributes - redirect to login or return error
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\": \"Missing required session attributes. Please login again.\"}");
//            return;
//        }
//
//        String naam = naamObj.toString();
//        String email = emailObj.toString();
//        String postcode = postcodeObj.toString();
//        String huisnummer = huisnummerObj.toString();
//
//        // Validate that none of the values are empty strings
//        if (naam.trim().isEmpty() || email.trim().isEmpty() || postcode.trim().isEmpty() || huisnummer.trim().isEmpty()) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\": \"All fields are required and cannot be empty.\"}");
//            return;
//        }
//
//        User user = new User();
//        user.setNaam(naam);
//        user.setEmail(email);
//        user.setPostcode(postcode);
//
//        try {
//            user.setHuisnummer(Integer.parseInt(huisnummer));
//        } catch (NumberFormatException e) {
//            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
//            resp.getWriter().write("{\"error\": \"Invalid house number format.\"}");
//            return;
//        }
//
//        System.out.println(user);
//    }
}
