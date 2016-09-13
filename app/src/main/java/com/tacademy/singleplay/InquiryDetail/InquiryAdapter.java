//package com.tacademy.singleplay.inquirydetail;
//
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.InquiryData;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Tacademy on 2016-08-24.
// */
//public class InquiryAdapter extends RecyclerView.Adapter<com.tacademy.singleplay.inquirydetail.InquiryHolder> implements com.tacademy.singleplay.inquirydetail.InquiryHolder.OnShowItemClickListener {
//
//    List<InquiryData> items = new ArrayList<>();
//
//
//    public void add(InquiryData inquiryData) {
//        items.add(inquiryData);
//        notifyDataSetChanged();
//    }
//
//    public InquiryAdapter(){
//
//    }
//
//    @Override
//    public com.tacademy.singleplay.inquirydetail.InquiryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_inquiry, null);
//        com.tacademy.singleplay.inquirydetail.InquiryHolder ih = new com.tacademy.singleplay.inquirydetail.InquiryHolder(view);
//        ih.setOnShowItemClickListener(this);
//        return ih;
//    }
//
//    @Override
//    public void onBindViewHolder(com.tacademy.singleplay.inquirydetail.InquiryHolder holder, int position) {
//        holder.setData(items.get(position));
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    public interface OnShowAdapterItemClickLIstener {
//        public void onShowAdapterItemClick(View view, InquiryData inquiryData, int position);
//    }
//    OnShowAdapterItemClickLIstener listener;
//    public void setOnAdapterItemClickListener(OnShowAdapterItemClickLIstener listener) {
//        this.listener = listener;
//    }
//    @Override
//    public void onShowItemClick(View view, InquiryData inquiryData, int position) {
//        if (listener != null) {
//            listener.onShowAdapterItemClick(view, inquiryData, position);
//        }
//
//    }
//
//
//}
