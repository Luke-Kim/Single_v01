package com.tacademy.singleplay.manager;

import android.text.TextUtils;


/**
 * Created by Tacademy on 2016-08-10.
 */
public class BookingManager {

    private static BookingManager instance;
    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    private String playId;
    private String playName;
    private String usableSeatNo;
    private String seatClass;
    private String booker;
    private String bookerPhone;
    private String bookerEmail;
    private String useCoupon;
    private String useMileage;
    private String settlement;
    private int totalPrice;
    private int oriPrice;
    private int couponPercent;
    private String rid;

    //        playId           공연 ID
//        playName      공연 이름
//        usableSeatNo  선택한 좌석
//        seatClass        좌석 등급
//        booker           예약자 이름
//        bookerPhone   예약자 전화번호
//        bookerEmail    예약자 이메일
//        useCoupon     사용한 쿠폰 ID
//        useMileage     사용한 마일리지
//        settlement      금액


    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }

    public String getUsableSeatNo() {
        return usableSeatNo;
    }

    public void setUsableSeatNo(String usableSeatNo) {
        this.usableSeatNo = usableSeatNo;
    }

    public String getSeatClass() {
        return seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public String getBooker() {
        return booker;
    }

    public void setBooker(String booker) {
        this.booker = booker;
    }

    public String getBookerPhone() {
        return bookerPhone;
    }

    public void setBookerPhone(String bookerPhone) {
        this.bookerPhone = bookerPhone;
    }

    public String getBookerEmail() {
        return bookerEmail;
    }

    public void setBookerEmail(String bookerEmail) {
        this.bookerEmail = bookerEmail;
    }

    public String getUseCoupon() {
        if (TextUtils.isEmpty(useCoupon)) {
            return "0";
        }
        return useCoupon;
    }

    public void setUseCoupon(String useCoupon) {
        this.useCoupon = useCoupon;
    }

    public String getUseMileage() {
        if (TextUtils.isEmpty(useMileage)) {
            return "0";
        } else {
            return useMileage;
        }
    }

    public void setUseMileage(String useMileage) {
        this.useMileage = useMileage;
//        setTotalPrice();
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }


    public int getOriPrice() {
        return oriPrice;
    }

    public void setOriPrice(int oriPrice) {
        this.oriPrice = oriPrice;
    }

    public int getCouponPercent() {
        return couponPercent;
    }

    public void setCouponPercent(int couponPercent) {
        this.couponPercent = couponPercent;
//        setTotalPrice();
    }



}
