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
        request.getSession().setAttribute("editAdId", request.getParameter("edit"));
        request.getRequestDispatcher("/WEB-INF/ads/editAd.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

            long adId = Long.parseLong(request.getParameter("adToEdit"));
            User user = (User) request.getSession().getAttribute("user");
            Ad ad = DaoFactory.getAdsDao().search(adId);
            List<String> categories = new ArrayList<>();

            //checks to make user that is edited was made by the logged in user
            if(ad.getUserId() == user.getId()){

              for(int i=1; i <= 5; i++){
                  String category = request.getParameter("category" + i);
                  if(category != null){
                      categories.add(category);

                  }
              }



            }


            // Is this needed and does it need to be modified
//            boolean inputHasErrors = username.isEmpty()
//                    || email.isEmpty()
//                    || password.isEmpty()
//                    || (!password.equals(passwordConfirmation));
//            // This too???
//            if (inputHasErrors) {
//                response.sendRedirect("/update-profile");
//                return;
//            }

        // update ad
//        DaoFactory.getAdsDao().editAd(ad);
//        response.sendRedirect("/ads");

        //Tells the page to go to the url /ads. Since there is no .jsp at the end we are going through another servlet
        response.sendRedirect("/ads");
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);

    }
}
