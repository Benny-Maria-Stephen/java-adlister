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

@WebServlet(name = "ViewAdsInProfile", urlPatterns = "/profile-viewAds")
public class ViewAdsInProfile extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String adTitle = request.getParameter("adTitle");
        List<Ad> ad = DaoFactory.getAdsDao().getAdTitles(adTitle);
        if(ad != null){
            request.getSession().setAttribute("title", adTitle);

        }
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
