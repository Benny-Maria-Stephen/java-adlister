package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface Ads {
    // get a list of all the ads
    List<Ad> all();
    // insert a new ad and return the new ad's id
    Long insert(Ad ad);

    List<Ad> search(String searchTerm);

    Long insertCategory(String category) throws SQLException;

    List<Long> catIds(List<String> categories);
    List<Ad> getAdTitles(User user);
    Ad search(long adId);

    boolean deleteAd(long adId);

//    boolean editAd(long adId);

    List<String> allCategories();

    void editAd(Ad ad, long adId);
}
