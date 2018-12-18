package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
//import com.codeup.adlister.com.codeup.adlister.Config;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLAdsDao implements Ads {
    private Connection connection = null;

    public MySQLAdsDao(Config config) {
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
    public List<Ad> all() {
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement("SELECT * FROM ads");
            ResultSet rs = stmt.executeQuery();
            return createAdsFromResults(rs);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    @Override
    public Long insert(Ad ad) {
        try {
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, ad.getUserId());
            stmt.setString(2, ad.getTitle());
            stmt.setString(3, ad.getDescription());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }

    private Ad extractAd(ResultSet rs) throws SQLException {
        return new Ad(
            rs.getLong("id"),
            rs.getLong("user_id"),
            rs.getString("title"),
            rs.getString("description")
        );
    }

    private List<Ad> createAdsFromResults(ResultSet rs) throws SQLException {
        List<Ad> ads = new ArrayList<>();
        while (rs.next()) {
            ads.add(extractAd(rs));
        }
        return ads;
    }

    public List<Ad> search(String title, Long adId, String category, String username) {
        PreparedStatement stmt = null;
        List<Ad> ads = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE title LIKE ? " +
                    "|| id = ? " +
                    "|| id IN (" +
                        "SELECT ad_id " +
                        "FROM ads_categories where category_id in (" +
                            "SELECT id FROM categories WHERE category=?)) || user_id IN (" +
                    "      SELECT id FROM users" +
                    "      WHERE username=?" +
                    "    );");
            String searchTermWithWildcards = "%" + title + "%";
            stmt.setString(1, searchTermWithWildcards);
            stmt.setLong(2, adId);
            stmt.setString(3, category);
            stmt.setString(4, username);


            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Long id = rs.getLong("id");
                Long userId = rs.getLong("user_id");
                String adTitle = rs.getString("title");
                String description = rs.getString("description");
                Ad ad = new Ad(id, userId, adTitle, description);
                ads.add(ad);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
        return ads;
    }
}
