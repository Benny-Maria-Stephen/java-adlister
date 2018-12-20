package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/view-ad")
public class ViewAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Long adId = Long.parseLong(request.getParameter("adId"));
        Ad ad = DaoFactory.getAdsDao().search(adId);

        if(ad != null){
            request.getSession().setAttribute("title", ad.getTitle());
            request.getSession().setAttribute("description", ad.getDescription());
            request.getSession().setAttribute("categories", ad.getCategories());
            request.getRequestDispatcher("/WEB-INF/ads/adIndex.jsp").forward(request, response);
        }
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
