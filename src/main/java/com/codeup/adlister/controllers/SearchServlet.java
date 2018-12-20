package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "controllers.SearchServlet", urlPatterns = "/search")
public class SearchServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");
        List<Ad> adsFound = DaoFactory.getAdsDao().search(searchTerm);

        if(adsFound.isEmpty()) {
            adsFound = DaoFactory.getAdsDao().all();
        }
        request.getSession().setAttribute("ads", adsFound);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

}