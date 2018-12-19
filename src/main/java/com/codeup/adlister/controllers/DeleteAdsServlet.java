package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete ")

public class DeleteAdsServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            response.sendRedirect("/login");
            return;
        }
        request.getRequestDispatcher("/WEB-INF/ads/create.jsp")
                .forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<String> categories = new ArrayList<>();

        for(int i = 1; i <= 5 ; i++){
            String category = request.getParameter("category" + i);
            if(category == null){
                break;
            }
            categories.add(category);
        }


        Ad ad = new Ad(
                user.getId(),
                request.getParameter("title"),
                request.getParameter("description"),
                categories
        );

        DaoFactory.getAdsDao().insert(ad);
        response.sendRedirect("/ads");
    }
}
