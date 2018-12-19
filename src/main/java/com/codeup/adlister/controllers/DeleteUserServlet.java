package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DeleteUserServlet", urlPatterns = "/update-profile")
public class DeleteUserServlet extends HttpServlet {
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
//        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        User user = (User) request.getSession().getAttribute("user");
        request.getSession();

        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (!password.equals(passwordConfirmation));
        if (inputHasErrors) {
            response.sendRedirect("/update-profile");
            return;
        }
        // delete profile
        DaoFactory.getUsersDao().deleteUser(user.getId());
        response.sendRedirect("/login");
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request, response);
    }
}
