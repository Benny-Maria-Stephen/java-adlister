package com.codeup.adlister.dao;

import com.codeup.adlister.models.Ad;

import java.util.ArrayList;
import java.util.List;

public class ListAdsDao implements Ads {
    private List<Ad> ads;

    public List<Ad> all() {
        if (ads == null) {
            ads = generateAds();
        }
        return ads;
    }

    public Long insert(Ad ad) {
        // make sure we have ads
        if (ads == null) {
            ads = generateAds();
        }
        // we'll assign an "id" here based on the size of the ads list
        // really the dao would handle this
        ad.setId((long) ads.size());
        ads.add(ad);
        return ad.getId();
    }

    @Override
    public List<Ad> search(String searchTerm) {
        return null;
    }

    @Override
    public Long insertCategory(String category) {
        return null;
    }

    @Override
    public List<Long> catIds(List<String> categories) {
        return null;
    }

    @Override
    public Ad search(long adId) {
        return null;
    }

    public Long insert(String category) {
        return null;
    }

    public List<Long> catAdded(List<String> categories) {
        List<Long> u = new ArrayList<>();
        u.add(-1L);
        return u;
    }

    private List<Ad> generateAds() {
        List<Ad> ads = new ArrayList<>();
        ads.add(new Ad(
            1,
            1,
            "playstation for sale",
            "This is a slightly used playstation"
        ));
        ads.add(new Ad(
            2,
            1,
            "Super Nintendo",
            "Get your game on with this old-school classic!"
        ));
        ads.add(new Ad(
            3,
            2,
            "Junior Java Developer Position",
            "Minimum 7 years of experience required. You will be working in the scripting language for Java, JavaScript"
        ));
        ads.add(new Ad(
            4,
            2,
            "JavaScript Developer needed",
            "Must have strong Java skills"
        ));
        return ads;
    }

    @Override
    public boolean deleteAd( long adId ) {
        return false;
    }

    @Override
    public boolean editAd( long adId ) {
        return false;
    }

    @Override
    public void editAd( Ad ad ) {

    }
}
