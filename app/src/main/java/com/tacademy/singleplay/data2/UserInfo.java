package com.tacademy.singleplay.data2;

public class UserInfo {
    private String phone;
    private int coupons;
    private String name;
    private int[] theme;
    private int[] day;
    private String email;
    private int mileage;
    private String noti;

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCoupons() {
        return this.coupons;
    }

    public void setCoupons(int coupons) {
        this.coupons = coupons;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int[] getTheme() {
        return this.theme;
    }

    public void setTheme(int[] theme) {
        this.theme = theme;
    }

    public int[] getDay() {
        return this.day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
