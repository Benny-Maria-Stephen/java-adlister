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
        Long editId = Long.parseLong(request.getParameter("edit"));
        Ad ad = DaoFactory.getAdsDao().search(editId);

        User user = (User) request.getSession().getAttribute("user");

        List<String> allCategories = DaoFactory.getAdsDao().allCategories();


//        note for later: make it
        if(ad.getUserId() == user.getId()){
            request.getSession().setAttribute("adToEdit", ad);
            request.getSession().setAttribute("adToEditID", ad.getUserId());

            for(String category : allCategories){
                if(ad.getCategories().contains(category)){
                    System.out.println(category);
                    request.getSession().setAttribute(category, true);
                    System.out.println(request.getSession().getAttribute(category));
                }
            }
            request.getSession().setAttribute("categories",allCategories);
            request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
        }

        //placeholder 404 or error message
        response.sendRedirect("/login");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//        has not been tested yet
            long adId = Long.parseLong(request.getParameter("adToEditID"));
            User user = (User) request.getSession().getAttribute("user");
            Ad ad = DaoFactory.getAdsDao().search(adId);
            List<String> allCategories = DaoFactory.getAdsDao().allCategories();
            List<String> categories = new ArrayList<>();

            //checks to make user that is edited was made by the logged in user
            if(ad.getUserId() == user.getId()){
              String title = request.getParameter("title");
              String description = request.getParameter("description");
              for(String category : allCategories){
                  if(Boolean.parseBoolean(request.getParameter(category))){
                      System.out.println(request.getParameter(category));
                      categories.add(category);
                  }

//                  Keep in mind the user may not want to change all the information and so we have populated the form
                  //maybe check if the information in the form is different for any fields on the form
                  //so maybe have different update queries depending on the information given
              }

              Ad newAd = new Ad(user.getId(), title, description, categories);
              DaoFactory.getAdsDao().editAd(newAd, ad.getId());

            }
        //Tells the page to go to the url /ads. Since there is no .jsp at the end we are going through another servlet
//        response.sendRedirect("/ads");
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);

    }
}
