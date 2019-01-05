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

@WebServlet(name = "controllers.EditAdServlet", urlPatterns = "/ads/edit")

public class EditAdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

            String id = request.getParameter("id");
//            String username = request.getParameter("username");
//            Long
//            String email = request.getParameter("email");
//            String password = request.getParameter("password");
//            String passwordConfirmation = request.getParameter("confirm_password");
//            User user = (User) request.getSession().getAttribute("user");

            request.getSession();


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
