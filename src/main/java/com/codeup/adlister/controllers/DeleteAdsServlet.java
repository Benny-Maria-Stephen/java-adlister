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
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "controllers.DeleteAdServlet", urlPatterns = "/ads/delete")

public class DeleteAdsServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean deleted = (boolean) request.getSession().getAttribute("deleted");
        System.out.println(deleted);
        if (deleted) {
            request.getRequestDispatcher("/WEB-INF/ads/deleted.jsp").forward(request, response);
        } else {
            response.sendRedirect("/ads");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        long adId = Long.parseLong(request.getParameter("delete"));
        boolean deleted = DaoFactory.getAdsDao().deleteAd(adId);
        request.setAttribute("deleted", deleted);
        System.out.println(deleted);
        request.getRequestDispatcher("/ads/delete").forward(request, response);
    }

}
