package com.tacademy.singleplay.pay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.Discount;
import com.tacademy.singleplay.data2.DiscountCoupons;
import com.tacademy.singleplay.data2.ShowList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class CouponHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.text_percent)
    TextView percentView;

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
