package com.tacademy.singleplay.data2;

public class ShowList {
    private int code;
    private ShowListReview[] review;
    private ShowListResults[] results;

    public int getCode() {
        return this.code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ShowListReview[] getReview() {
        return this.review;
    }

    public void setReview(ShowListReview[] review) {
        this.review = review;
    }

    public ShowListResults[] getResults() {
        return this.results;
    }

    public void setResults(ShowListResults[] results) {
        this.results = results;
    }
}
