//package com.tacademy.singleplay.bookingdetail;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.Checkable;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.SeatData;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by Tacademy on 2016-08-30.
// */
//public class SeatInfoViewHolder extends RecyclerView.ViewHolder implements Checkable {
//
//    @BindView(R.id.txt_show_info)
//    TextView infoView;
//    @BindView(R.id.img_check)
//    ImageView checkImage;
//    @BindView(R.id.txt_show_price)
//    TextView textView;
//
//    public interface OnShowItemClickListener {
//        public void onShowItemClick(View view, SeatData seatData, int position);
//    }
//
//    OnShowItemClickListener mListener;
//    public void setOnShowItemClickListener(OnShowItemClickListener listener) { //리스너 등록함수
//        this.mListener = listener;
//    }
//
//    boolean isChecked;
//    public void setChecked(boolean checked){
//        if(isChecked != checked) {
//            isChecked = checked;
//            drawCheck();
//        }
//    }
//
//
//    public void drawCheck() {
//        if (isChecked) {
//            checkImage.setImageResource(android.R.drawable.checkbox_on_background);
//        } else {
//            checkImage.setImageResource(android.R.drawable.checkbox_off_background);
//        }
//    }
//
//    public SeatInfoViewHolder(View itemView) {
//        super(itemView);
//        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                int position = getAdapterPosition();
//                if(mListener != null) {
//                    mListener.onShowItemClick(view, seatData, getAdapterPosition());
//                }
//            }
//
//
//        });
//    }
//    SeatData seatData;
//    public void setData(SeatData seatData) {
//        this.seatData = seatData;
//        textView.setText(seatData.getSeatPrice());
//        infoView.setText(seatData.getSeatInfo());
//    }
//    public boolean isChecked() {
//        return isChecked;
//    }
//
//
//    @Override
//    public void toggle() {
//        setChecked(!isChecked);
//    }
//}
