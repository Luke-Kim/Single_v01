package com.tacademy.singleplay.data;

public class BookingList {
    private int salePrice;
    private int price;
    private String playName;
    private String playTime;
    private double starScore;
    private int rsvId;
    private String playDay;
    private String placeName;
    private String poster;

    public int getSalePrice() {
        return this.salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getPlayName() {
        return this.playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public double getStarScore() {
        return this.starScore;
    }

    public void setStarScore(double starScore) {
        this.starScore = starScore;
    }

    public int getRsvId() {
        return this.rsvId;
    }

    public void setRsvId(int rsvId) {
        this.rsvId = rsvId;
    }

    public String getPlayDay() {
        return this.playDay;
    }

    public void setPlayDay(String playDay) {
        this.playDay = playDay;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public String getPoster() {
        return this.poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }
}
