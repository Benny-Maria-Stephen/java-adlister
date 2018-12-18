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
        List<Ad> adsFound = DaoFactory.getAdsDao().search(searchTerm, null, searchTerm, null);

        if(adsFound != null){
            request.getSession().setAttribute("ads", adsFound);

            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        } else{
            adsFound = DaoFactory.getAdsDao().all();
            request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

//        String searchTerm = request.getParameter("title");
////        Long adId = request.getParameter("id")
////        Integer searchOption = request.getParameter("searchOption");
//
////        List<Ad> adsFound = DaoFactory.getAdsDao().search(searchTerm, null, searchTerm, null);
//
////        switch (searchOption){
////            case 1:
////
////                break;
////            case 2:
////                break;
////            case 3:
////                break;
////        }


    }
}
