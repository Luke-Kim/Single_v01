package com.tacademy.singleplay.data;

public class Coupon {
    private String couponName;
    private int couponNo;
    private int saveOff;
    private String periodStart;
    private String periodEnd;

    public String getCouponName() {
        return this.couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public int getSaveOff() {
        return this.saveOff;
    }

    public void setSaveoff(int saveOff) {
        this.saveOff = saveOff;
    }

    public int getCouponNo() {
        return this.couponNo;
    }

    public void setCouponNo(int couponNo) {
        this.couponNo = couponNo;
    }

    public String getPeriodStart() {
        return this.periodStart;
    }

    public void setPeriodStart(String periodStart) {
        this.periodStart = periodStart;
    }

    public String getPeriodEnd() {
        return this.periodEnd;
    }

    public void setPeriodEnd(String periodEnd) {
        this.periodEnd = periodEnd;
    }
}
