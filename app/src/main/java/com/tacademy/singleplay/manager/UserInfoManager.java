package com.tacademy.singleplay.manager;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class UserInfoManager {
    private static UserInfoManager instance;
    public static UserInfoManager getInstance() {
        if (instance == null) {
            instance = new UserInfoManager();
        }
        return instance;
    }

    private String name;
    private String email;
    private String phone;
    private int coupons;
    private int mileage;
    private int[] day;
    private int[] theme;
    private String noti;
    private String wishnoti;

    public String getWishnoti() {
        return wishnoti;
    }

    public void setWishnoti(String wishnoti) {
        this.wishnoti = wishnoti;
    }

    public String getNoti() {
        return noti;
    }

    public void setNoti(String noti) {
        this.noti = noti;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getCoupons() {
        return coupons;
    }

    public void setCoupons(int coupons) {
        this.coupons = coupons;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public int[] getDay() {
        return day;
    }

    public void setDay(int[] day) {
        this.day = day;
    }

    public int[] getTheme() {
        return theme;
    }

    public void setTheme(int[] theme) {
        this.theme = theme;
    }
}
