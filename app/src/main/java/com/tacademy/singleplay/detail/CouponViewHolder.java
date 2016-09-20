package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.Coupon;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class CouponViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.txt_couponName)
    TextView txt_couponName;
    @BindView(R.id.coupon_perc)
    TextView txt_coupon_perc;
    @BindView(R.id.txt_periodStart)
    TextView txt_periodStart;
    @BindView(R.id.txt_periodEnd)
    TextView txt_periodEnd;


    public CouponViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    Coupon coupon;
    public void setData(Coupon coupon) {
        this.coupon = coupon;
//        image_coupon.setImageResource(coupon.getImage());
        txt_couponName.setText(coupon.getCouponName());
        txt_coupon_perc.setText(coupon.getSaveOff() + "%");
        txt_periodStart.setText(coupon.getPeriodStart());
        txt_periodEnd.setText(coupon.getPeriodEnd());
    }
}
