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
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        Long id = (Long)request.getSession().getAttribute("id");
//
        request.getSession();
//
//        // validate input
//        // is email empty, password,
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (! password.equals(passwordConfirmation));

        if (inputHasErrors) {
            response.sendRedirect("/update-profile");
            return;
        }
        //        // create and save a new user
        User user = new User(id, username, email, password);
        DaoFactory.getUsersDao().updateUser(user);
        response.sendRedirect("/");
//
//        request.getRequestDispatcher("/WEB-INF/partials/profile.jsp").forward(request, response);
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request, response);
//        if (inputHasErrors) {
//            response.sendRedirect("/WEB-INF/updateProfile.jsp");
//            return;
//        }
//


    }
}