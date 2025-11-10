package org.example;


import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null)
        {
            sb.append(line);
        }
        String jsonBody = sb.toString();
        Gson gson = new Gson();
        User userData = gson.fromJson(jsonBody, User.class);

        System.out.println(userData.toString());
    }
}
