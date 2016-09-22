package com.tacademy.singleplay.bookingdetail;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EmptySeatInfo;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EmptySeatHolder extends RecyclerView.ViewHolder
                                implements Checkable{
    @BindView(R.id.text_class)
    TextView classView;
    @BindView(R.id.text_seatInfo)
    TextView seatInfoView;
    @BindView(R.id.text_price)
    TextView priceView;
    @BindView(R.id.img_check)
    ImageView checkImage;

    public interface OnSeatItemClickListener {
        public void onSeatItemClick(View view, EmptySeatInfo emptySeatInfo, int position);
    }

    OnSeatItemClickListener listener;
    public void setOnSeatItemClickListener(OnSeatItemClickListener listener) {
        this.listener = listener;
    }

    public EmptySeatHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSeatItemClick(view, seatList, getAdapterPosition());
            }
        });
    }

    boolean isChecked;
    public void setChecked(boolean checked){
        if(isChecked != checked) {
            isChecked = checked;
            drawCheck();

        }
    }

    public void drawCheck() {
        if (isChecked) {
            checkImage.setImageResource(R.drawable.radio_btn_on);
            priceView.setTextColor(Color.RED);
        } else {
            checkImage.setImageResource(R.drawable.radio_btn_off);
            priceView.setTextColor(Color.LTGRAY);
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

    EmptySeatInfo seatList;
    public void setData(EmptySeatInfo seatList) {
        this.seatList = seatList;
        classView.setText(seatList.getSeatClass());
        seatInfoView.setText(seatList.getSeatInfo());
        priceView.setText(""+seatList.getPrice());
//        priceView.setText(EmptySeat.class.);

    }
}
