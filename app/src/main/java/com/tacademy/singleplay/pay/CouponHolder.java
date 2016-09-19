package com.tacademy.singleplay.pay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.DiscountCoupons;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CouponHolder extends RecyclerView.ViewHolder implements Checkable{
    @BindView(R.id.text_percent)
    TextView percentView;
    @BindView(R.id.coupon_img)
    ImageView coupon_img;

    boolean isChecked;
    @Override
    public void setChecked(boolean checked) {
        if(isChecked != checked) {
            isChecked = checked;
            drawCheck();
        }
    }

    public void drawCheck() {
        if(isChecked) {
            coupon_img.setImageResource(R.drawable.pay_coupon);
        } else {
            coupon_img.setImageResource(R.drawable.pay_coupon_n);
        }
    }

    @Override
    public boolean isChecked() {
        return isChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked);
    }

    public interface OnCouponItemClickListener {
        public void onCouponItemClick(View view, DiscountCoupons discountCoupons, int position);
    }

    OnCouponItemClickListener listener;
    public void setOnCouponItemClickListener(OnCouponItemClickListener listener) {
        this.listener = listener;
    }

    public CouponHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onCouponItemClick(view, discountCoupons, getAdapterPosition());
            }
        });
    }
    DiscountCoupons discountCoupons;
    public void setData(DiscountCoupons discountCoupons) {
        this.discountCoupons = discountCoupons;
        percentView.setText("" + discountCoupons.getSaveOff()+"%");
    }

}
