package com.tacademy.singleplay.data;

public class EmptySeatInfo {
    private String seatClass;
    private int price;
    private int usableSeatNo;
    private String seatInfo;

    public String getSeatClass() {
        return this.seatClass;
    }

    public void setSeatClass(String seatClass) {
        this.seatClass = seatClass;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getUsableSeatNo() {
        return this.usableSeatNo;
    }

    public void setUsableSeatNo(int usableSeatNo) {
        this.usableSeatNo = usableSeatNo;
    }

    public String getSeatInfo() {
        return this.seatInfo;
    }

    public void setSeatInfo(String seatInfo) {
        this.seatInfo = seatInfo;
    }
}
