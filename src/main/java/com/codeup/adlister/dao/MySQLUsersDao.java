package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.User;
import com.mysql.cj.jdbc.Driver;

import java.sql.*;

public class MySQLUsersDao implements Users {
    private Connection connection;

    public MySQLUsersDao(Config config) {
        try {
            DriverManager.registerDriver(new Driver());
            connection = DriverManager.getConnection(
                config.getUrl(),
                config.getUser(),
                config.getPassword()
            );
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database!", e);
        }
    }

    @Override
    public User findByUsername(String username) {
        String query = "SELECT * FROM users WHERE username = ? LIMIT 1";
        try {
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            return extractUser(stmt.executeQuery());
        } catch (SQLException e) {
            throw new RuntimeException("Error finding a user by username", e);
        }
    }

    @Override
    public Long insert(User user) {
        String query = "INSERT INTO users(username, email, password) VALUES (?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getPassword());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating new user", e);
        }
    }

//    public Long deleteUser(User user){
//            String query = "DELETE FROM users WHERE id = ?";
//        try {
//            // prepare statement
//            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//            //set params
//            stmt.setLong(1, user.getId());
//            stmt.execute(query);
//
//            ResultSet rs = stmt.getGeneratedKeys();
//            rs.next();
//            deleteUser(user);
//            System.out.println("Goodbye!");
//            return rs.getLong(1);
//        } catch (SQLException e) {
//            throw new RuntimeException("Error deleting user", e);
//        }
//    }

    @Override
    public void updateUser(User user) {
        String sql = "UPDATE `users` SET `username`=?, `email`= ? where `id`=?";
        try {
            // prepare statement
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //set params
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getEmail());
            stmt.setLong(3, user.getId());
            // execute SQL
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating profile", e);
        }
    }

    public void deleteUser( User user ) {
        String query = "DELETE FROM users WHERE id = ?";
        try {
            // prepare statement
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            //set params
            stmt.setLong(1, user.getId());
            stmt.execute(query);

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            deleteUser(user);
            System.out.println("Goodbye!");
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting user", e);
        }
    }


    private User extractUser(ResultSet rs) throws SQLException {
        if (! rs.next()) {
            return null;
        }
        return new User(
            rs.getLong("id"),
            rs.getString("username"),
            rs.getString("email"),
            rs.getString("password")
        );
    }
}
