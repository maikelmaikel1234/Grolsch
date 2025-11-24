package org.example;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/ajaxHome")
public class HomePageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HomePage homePage = new HomePage(req, resp);
        homePage.render();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String naam = req.getSession().getAttribute("naam").toString();
        String email = req.getSession().getAttribute("email").toString();
        String postcode = req.getSession().getAttribute("postcode").toString();
        String huisnummer = req.getSession().getAttribute("huisnummer").toString();

        User user = new User();
        user.setNaam(naam);
        user.setEmail(email);
        user.setPostcode(postcode);
        user.setHuisnummer(Integer.parseInt(huisnummer));

        System.out.println(user);
    }
}
