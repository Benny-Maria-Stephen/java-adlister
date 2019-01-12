package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/edit")

public class EditAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        request.getSession().setAttribute("editAdId", request.getParameter("edit"));
        Long editId = Long.parseLong(request.getParameter("edit"));
        Ad ad = DaoFactory.getAdsDao().search(editId);
        request.getSession().setAttribute("ad", ad);
        User user = (User) request.getSession().getAttribute("user");
        if(user != null && ad.getUserId() == user.getId()){
            request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);

        }
        //placeholder 404 or error message
        response.sendRedirect("/");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        has not been tested yet
            long adId = Long.parseLong(request.getParameter("adToEdit"));
            User user = (User) request.getSession().getAttribute("user");
            Ad ad = DaoFactory.getAdsDao().search(adId);
            List<String> categories = new ArrayList<>();

            //checks to make user that is edited was made by the logged in user
            if(ad.getUserId() == user.getId()){
              for(int i=1; i <= 5; i++){
                  String category = request.getParameter("category" + i);
                  if(category != null){
                      System.out.println(category);
                      categories.add(category);
                  }

                  String title = request.getParameter("title");
                  String description = request.getParameter("description");
                  Ad newAd = new Ad(user.getId(), title, description, categories);
                  DaoFactory.getAdsDao().editAd(newAd, ad.getId());
//                  Keep in mind the user may not want to change all the information and so we have populated the form
                  //maybe check if the information in the form is different for any fields on the form
                  //so maybe have different update queries depending on the information given
              }



            }
        //Tells the page to go to the url /ads. Since there is no .jsp at the end we are going through another servlet
//        response.sendRedirect("/ads");
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);

    }
}
