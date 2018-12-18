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
        request.getAttribute("ad");
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String searchTerm = request.getParameter("searchTerm");

        String title = request.getParameter("title");
//        String cat = request.getParameter("category");


//        List<Ad> adsFound= DaoFactory.getAdsDao().search(title);
//        request.setAttribute("ads");

//        response.sendRedirect("/search?ad=" + );
    }
}


//@WebServlet(name = "controllers.AdsIndexServlet", urlPatterns = "/ads")
//public class AdsIndexServlet extends HttpServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setAttribute("ads", DaoFactory.getAdsDao().all());
//        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
//    }
//}

//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        User user = (User) request.getSession().getAttribute("user");
//        Ad ad = new Ad(
//                user.getId(),
//                request.getParameter("title"),
//                request.getParameter("description")
//        );
//        DaoFactory.getAdsDao().insert(ad);
//        response.sendRedirect("/ads");
//    }
