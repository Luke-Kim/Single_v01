package com.tacademy.singleplay.manager;

import android.preference.Preference;

/**
 * Created by Tacademy on 2016-09-13.
 */
public class LoginCheckManager {
    private static LoginCheckManager instance;
    public static LoginCheckManager getInstance() {
        if (instance == null) {
            instance = new LoginCheckManager();
        }
        return instance;
    }

    boolean checkLogin;
    String userName;
    int couponCnt;
    int mileage;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isCheckLogin() {
        return checkLogin;
    }

    public void setCheckLogin(boolean checkLogin) {
        this.checkLogin = checkLogin;
    }

    public int getCouponCnt() {
        return couponCnt;
    }

    public void setCouponCnt(int couponCnt) {
        this.couponCnt = couponCnt;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
