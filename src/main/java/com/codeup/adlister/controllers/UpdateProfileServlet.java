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
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");
        User user = (User) request.getSession().getAttribute("user");
        request.getSession();

        // Is this needed and does it need to be modified
        boolean inputHasErrors = username.isEmpty()
                || email.isEmpty()
                || password.isEmpty()
                || (!password.equals(passwordConfirmation));
        // This too???
        if (inputHasErrors) {
            response.sendRedirect("/update-profile");
            return;
        }

        // update profile
        User updatedUser = new User(user.getId(), username, email, password);
        DaoFactory.getUsersDao().updateUser(updatedUser);
        request.getSession().setAttribute("user", updatedUser);

        response.sendRedirect("/profile");

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/updateProfile.jsp").forward(request, response);
    }
}