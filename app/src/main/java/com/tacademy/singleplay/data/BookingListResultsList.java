package com.tacademy.singleplay.data;

public class BookingListResultsList {
    private int salePrice;
    private int price;
    private String playName;
    private double starScoreAvg;
    private String playTime;
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

    public double getStarScoreAvg() {
        return this.starScoreAvg;
    }

    public void setStarScoreAvg(double starScoreAvg) {
        this.starScoreAvg = starScoreAvg;
    }

    public String getPlayTime() {
        return this.playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
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
