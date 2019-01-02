package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;
//import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete")

public class DeleteAdsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long adId = Long.parseLong(request.getParameter("delete"));//grabs ad.id value from the "delete" button

        boolean deleted = DaoFactory.getAdsDao().deleteAd(adId); //calls function to delete ad using the ad id grabbed
        //from the delete button-function returns boolean as an indicator of whether or not the ad was successfully deleted

        //The goal is to set an attribute "deleted" to make sure the ad was deleted. However, setAttirbute requires
        //an object value so we must later change the MySQLAdsDao after the view ads on profile feature is done.
        //For now, we will use system.out.println so we know the ad was deleted.
        System.out.println(deleted);

        //Tells the page to go to the url /ads. Since there is no .jsp at the end we are going through another servlet
        response.sendRedirect("/ads");
//        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }

}
