package com.codeup.adlister.dao;

import com.codeup.adlister.Config;
import com.codeup.adlister.models.Ad;
import com.mysql.cj.jdbc.Driver;
//import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
            String insertAd = "INSERT INTO ads(user_id, title, description) VALUES (?, ?, ?)";
            PreparedStatement stmtAd = connection.prepareStatement(insertAd, Statement.RETURN_GENERATED_KEYS);
            stmtAd.setLong(1, ad.getUserId());
            stmtAd.setString(2, ad.getTitle());
            stmtAd.setString(3, ad.getDescription());//inserts ads without categories

            stmtAd.executeUpdate();
            ResultSet rs = stmtAd.getGeneratedKeys();
            rs.next();

            Long adId = rs.getLong(1);

            System.out.println(ad.getCategories());
            List<String> categories = ad.getCategories();
            System.out.println("Categories are going to get added to the list of category ids");
            List<Long> categoryIds = catIds(categories);
            if(insertCatAdIdPairs(adId, categoryIds)){
                System.out.println("Pairs inserted into ads_categories!");
            }

            return rs.getLong(1);
        } catch (SQLException e) {
            throw new RuntimeException("Error creating a new ad.", e);
        }
    }



    public List<Long> catIds(List<String> categories){
        //Goes through the list of category names and returns the category id if it exists or
        // ads that category and returns the generated ids
        List<Long> ids = new ArrayList<>();
        for(String category : categories){
            System.out.println(category);
            try {
                String catQuery = "SELECT id FROM categories WHERE category=?";
                PreparedStatement stmt = connection.prepareStatement(catQuery);
                stmt.setString(1, category);

                System.out.println(stmt);
                ResultSet rs = stmt.executeQuery();

                System.out.println(rs);

                if(rs.next()){//if any results are found
                    while(rs.next()){
                        ids.add(rs.getLong("id"));
                    }
                } else{//if a result is not found
                    //not working because the query comes back later than the program is run
                    System.out.println("This gets executed first");
                    ids.add(insertCategory(category));
                }

            } catch (SQLException e) {
                try {
                    System.out.println("first exception thrown");
                    ids.add(insertCategory(category));
                } catch (SQLException e1) {
                    System.out.println("second exception thrown");
                    e1.printStackTrace();
                }
                e.printStackTrace();
            }
        }
        return ids;
    }

    private boolean insertCatAdIdPairs(Long adId, List<Long> categoryIds){
        int rows = 0;
        for(Long categoryId : categoryIds){
            try{
                String insertPair = "INSERT INTO ads_categories (ad_id, category_id) VALUES (?, ?)";
                PreparedStatement stmt = connection.prepareStatement(insertPair);
                stmt.setLong(1, adId);
                stmt.setLong(2, categoryId);
                rows += stmt.executeUpdate();

            }catch (SQLException e){
                throw new RuntimeException("Error adding ad and category pair", e);
            }
        }
        return rows > 0;
    }


    public Long insertCategory(String category) throws SQLException{
//        try {
            String insertCat = "INSERT INTO categories(category) VALUES (?)";
            PreparedStatement stmt = null;
            stmt = connection.prepareStatement(insertCat, Statement.RETURN_GENERATED_KEYS);
            System.out.println(stmt);
            stmt.setString(1, category);
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            return rs.getLong(1);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
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

    public List<Ad> search(String searchTerm) {
        PreparedStatement stmt = null;
        List<Ad> ads = new ArrayList<>();

        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE title LIKE ? ||" +
                    "id IN (" +
                        "SELECT ad_id " +
                        "FROM ads_categories where category_id in (" +
                            "SELECT id FROM categories WHERE category=?)) || user_id IN (" +
                    "      SELECT id FROM users" +
                    "      WHERE username=?" +
                    "    );");

            String searchTermWithWildcards = "%" + searchTerm + "%";
            stmt.setString(1, searchTermWithWildcards);
            stmt.setString(2, searchTerm);
            stmt.setString(3, searchTerm);
//

            ResultSet rs = stmt.executeQuery();
            ads = createAdsFromResults(rs);
            return ads;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    public Ad search(long adId) {
        PreparedStatement stmt = null;
        Ad ad;

        try {
            stmt = connection.prepareStatement("SELECT * FROM ads WHERE id = ?");
            stmt.setLong(1, adId);

            ResultSet rs = stmt.executeQuery();
            rs.next();
            long id = rs.getLong("id");
            long userId = rs.getLong("user_id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            List<String> categories = getAdCategories(adId);

            ad = new Ad(id, userId, title, description, categories);
            return ad;
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving all ads.", e);
        }
    }

    private List<String> getAdCategories(long adId){
        List<String> categories = new ArrayList<>();
        try {
            String getCategoryIds = "SELECT category FROM categories WHERE id IN (SELECT category_id FROM ads_categories WHERE ad_id = ?)";
            PreparedStatement stmt = connection.prepareStatement(getCategoryIds);
            stmt.setLong(1, adId);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                categories.add(rs.getString("category"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
