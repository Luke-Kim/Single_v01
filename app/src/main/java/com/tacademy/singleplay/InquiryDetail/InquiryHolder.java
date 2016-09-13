//package com.tacademy.singleplay.inquirydetail;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.View;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.InquiryData;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
///**
// * Created by Tacademy on 2016-08-29.
// */
//public class InquiryHolder extends RecyclerView.ViewHolder {
//
//    @BindView(R.id.inq_title)
//    TextView inq_title;
//    public InquiryData iqData;
//    public ImageView btn_expand_toggle;
//
//
//    OnShowItemClickListener listener;
//
//    public interface OnShowItemClickListener { // 1.interface 정의
//        public void onShowItemClick(View view, InquiryData inquiryData, int position);
//        //callBack 함수, 첫번째 인자:눌린 view 넘김, 두번째:inquiry 넘김, 세번째:position 값 넘김
//    }
//
//    public void setOnShowItemClickListener(OnShowItemClickListener listener) { //리스너 등록함수
//        this.listener = listener;
//    }
//
//
//    public InquiryHolder(View itemView) {
//        super(itemView);
//        ButterKnife.bind(this, itemView);
//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //listener.onShowItemClick(view, inquiryData, getAdapterPosition());
//            }
//        });
//    }
//    InquiryData inquiryData;
//    public void setData(InquiryData inquiryData) {
//        this.inquiryData = inquiryData;
//        inq_title.setText(inquiryData.getTitle());
//    }
//}
