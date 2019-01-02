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

@WebServlet(name = "ViewAdsInProfileServlet", urlPatterns = "/profile-ads")
public class ViewAdsInProfileServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//                String adTitle = request.getParameter("adTitle");
//        List<Ad> ad = DaoFactory.getAdsDao().getAdTitles(adTitle);

        Long adId = Long.parseLong(request.getParameter("adId"));
        Ad ad = DaoFactory.getAdsDao().search(adId);
        if(ad != null){
            request.getSession().setAttribute("title", ad.getTitle());
//            request.getSession().setAttribute("title", adTitle);

        }
        request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
    }
}
