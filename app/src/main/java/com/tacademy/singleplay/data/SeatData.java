package com.tacademy.singleplay.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class SeatData implements Serializable {
    public String seatInfo;
    public String seatPrice;

    public SeatData(){

    }

    public SeatData(String seatInfo, String seatPrice){
        this.seatInfo = seatInfo;
        this.seatPrice = seatPrice;
    }

    public String getSeatInfo() {
        return this.seatInfo;
    }

    public void getSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }

    public String getSeatPrice() {
        return this.seatPrice;
    }

    public void setSeatPrice(String seatPrice) {
        this.seatPrice = seatPrice;
    }

}
