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
        long adId = Long.parseLong(request.getParameter("delete"));
        boolean deleted = DaoFactory.getAdsDao().deleteAd(adId);
        request.setAttribute("deleted", deleted);
        System.out.println(deleted);

        response.sendRedirect("/ads");
    }

}
