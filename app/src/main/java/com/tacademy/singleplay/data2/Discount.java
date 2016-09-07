package com.tacademy.singleplay.data2;

public class Discount {
    private DiscountCoupons[] coupons;
    private int mileage;

    public DiscountCoupons[] getCoupons() {
        return this.coupons;
    }

    public void setCoupons(DiscountCoupons[] coupons) {
        this.coupons = coupons;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }
}
