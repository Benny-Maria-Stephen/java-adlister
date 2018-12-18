package com.codeup.adlister.controllers;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.UpdateProfileServlet", urlPatterns = "/update-profile")
public class UpdateProfileServlet extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
//        String email = request.getParameter("email");
//        String password = request.getParameter("password");
////        String passwordConfirmation = request.getParameter("confirm_password");
//
//        request.getSession();
//
//        // validate input
//        // is email empty, password,
//
//        request.getRequestDispatcher("/WEB-INF/partials/profile.jsp").forward(request, response);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {

//        if (inputHasErrors) {
//            response.sendRedirect("/WEB-INF/updateProfile.jsp");
//            return;
//        }
//
//        // create and save a new user
//        User user = new User(username, email, password);
//        DaoFactory.getUsersDao().insert(user);
//        response.sendRedirect("/login");

    }
}
