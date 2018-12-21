package com.codeup.adlister.dao;

import com.codeup.adlister.models.User;

import java.sql.SQLException;
import java.util.List;


public interface Users {
    User findByUsername(String username);
    Long insert(User user);
    void updateUser(User user);
    void deleteUser(User user);
    User findByUserId(Long id);
}
