package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

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

    Ad search(long adId);

    boolean deleteAd(long adId);

}
