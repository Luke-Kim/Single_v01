package com.tacademy.singleplay.data2;

public class Profile {
    private String userPhone;
    private String userEmail;
    private String message;
    private String profileImg;
    private String userName;
    private int coupon;
    private int mileage;

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int getCoupon() {
        return this.coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    public String getUserPhone() {
        return this.userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserEmail() {
        return this.userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProfileImg() {
        return this.profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
