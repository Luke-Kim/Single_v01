//package com.tacademy.singleplay.inquirydetail;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.InquiryData;
//
//import java.util.List;
//
///**
// * Created by Tacademy on 2016-09-02.
// */
//public class InquiryRcAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
//
//    public static final int HEAD = 0;
//    public static final int CHILD = 1;
//    private List<InquiryData> items;
//
//    public InquiryRcAdapter(List<InquiryData> items) {
//        this.items = items;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // head와 child를 배치
//        View view = null;
//        Context context = parent.getContext();
//        /// child의 배치를 결정
//        float dp = context.getResources().getDisplayMetrics().density;
//        int subItemPaddingLeft = (int) (18 * dp);
//        int subItemPaddingTopAndBottom = (int) (5 * dp);
//        // head와 child의 layout을 배치
//        switch (viewType) {
//            case HEAD :
//                LayoutInflater inflater = (LayoutInflater)parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                view = inflater.inflate(R.layout.view_inquiry, parent, false);
//                InquiryHolder holder = new InquiryHolder(view);
//                return holder;
//            case CHILD :
//                ImageView imageView = new ImageView(context);
//                imageView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
//                imageView.setLayoutParams(
//                        new ViewGroup.LayoutParams(
//                                ViewGroup.LayoutParams.MATCH_PARENT,
//                                ViewGroup.LayoutParams.WRAP_CONTENT));
//                return new RecyclerView.ViewHolder(imageView){
//                };
//        }
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        final InquiryData inquiryData = items.get(position);
//        switch (inquiryData.type) {
//            case HEAD :
//                final InquiryHolder ih = (InquiryHolder)holder;
//                ih.iqData = inquiryData;
//                ih.inq_title.setText(inquiryData.title);
//                if(inquiryData.contain_image > 0){
//                    ih.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
//                } else {
//                    ih.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
//                }
//                ih.btn_expand_toggle.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        if(inquiryData.contain_image > 0 ){
//                            ih.btn_expand_toggle.setImageResource(R.drawable.circle_plus);
//                        } else {
//                            ih.btn_expand_toggle.setImageResource(R.drawable.circle_minus);
//                        }
//                    }
//                });
//                break;
//            case CHILD :
//                ImageView imageView = (ImageView)holder.itemView;
//                imageView.setImageResource(inquiryData.getContain_image());
//                break;
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return items.get(position).type;
//    }
//}
