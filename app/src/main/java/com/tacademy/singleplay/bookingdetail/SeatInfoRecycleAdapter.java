//package com.tacademy.singleplay.bookingdetail;
//
//import android.support.v7.widget.RecyclerView;
//import android.util.SparseBooleanArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.tacademy.singleplay.R;
//import com.tacademy.singleplay.data.SeatData;
//import com.tacademy.singleplay.data2.EmptySeatInfo;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Created by Tacademy on 2016-08-30.
// */
//public class SeatInfoRecycleAdapter extends RecyclerView.Adapter<SeatInfoViewHolder>
//        implements SeatInfoViewHolder.OnShowItemClickListener {
//    List<EmptySeatInfo> items = new ArrayList<>();
//
//    SparseBooleanArray itemSelected = new SparseBooleanArray();
//
//    public SeatInfoRecycleAdapter() {
//
//    }
//
//    public void addAll(EmptySeatInfo[] seatData) {
//        items.addAll(Arrays.asList(seatData));
//        notifyDataSetChanged();
//    }
//
//    int checkedPosition = INVALID_POSITION;
//    public static final int INVALID_POSITION = -1;
//    public static final int CHOICE_MODE_SINGLE = 0;
//    public static final int CHOICE_MODE_MULTIPLE = 1;
//    private int mode = CHOICE_MODE_SINGLE;
//
//    @Override
//    public SeatInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_seat_choose, parent, false);
//        SeatInfoViewHolder svh = new SeatInfoViewHolder(view);
//        svh.setOnShowItemClickListener(this);
//        return svh;
//    }
//
//    @Override
//    public void onBindViewHolder(SeatInfoViewHolder holder, int position) {
//        // RecyclerView의 item의 셋팅과 사용자가 스크롤링 할때, 호출되는 메서드.
//        // 내가 원하는 데이터가 포지션 별로 ArrayList<Mydata>에 저장되어 있으면 이러한 데이터를
//        // 포지션 별로 보여주는 것을 보장해준다.
////        holder.setData(items.get(position));
//        holder.setChecked(checkedPosition == position);
////        holder.setOnShowItemClickListener(listener);
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return items.size();
//    }
//
//    @Override
//    public void onShowItemClick(View view, SeatData seatData, int position) {
//        setSeatItemChecked(position, true);
//        if (listener != null) {
//            listener.onShowAdapterItemClick(view, seatData, position);
//        }
//    }
//
//
//    // ViewHolder에서 했던것 처럼 리스너 만든다.
//    public interface OnShowAdapterItemClickLIstener {
//        public void onShowAdapterItemClick(View view, SeatData seatData, int position);
//    }
//
//    OnShowAdapterItemClickLIstener listener;
//
//    public void setOnAdapterItemClickListener(OnShowAdapterItemClickLIstener listener) {
//        this.listener = listener;
//    }
//
//    public int getCheckedPosition() {
//        return checkedPosition;
//    }
//
//    private void setSeatItemChecked(int position, boolean isChecked) {
//        if (checkedPosition != position) {
//            if (isChecked) {
//                checkedPosition = position;
//                notifyDataSetChanged();
//            }
//        } else {
//            if (!isChecked) {
//                checkedPosition = INVALID_POSITION;
//                notifyDataSetChanged();
//            }
//        }
//    }
//
//
//    //    @Override
////    public void onShowItemClick(View view, int position) {
//////        boolean checked = !checkedItems.get(position);
//////        checkedItems.put(position, checked);
//////        notifyDataSetChanged();
////        setItemsChecked(position.true);
////
////    }
//    public SparseBooleanArray getCheckedItemsPositions() {
//        throw new IllegalArgumentException("invalid mode");
//    }
//
//    public void setItemsChecked(int position, boolean isChecked) {
//        if (checkedPosition != position) {
//            if (isChecked) {
//                checkedPosition = position;
//                notifyDataSetChanged();
//            }
//        } else {
//            if (!isChecked) {
//                checkedPosition = INVALID_POSITION;
//                notifyDataSetChanged();
//            }
//        }
//    }
////    public void setItemsChecked(int position, boolean isCheked){
////        if(checkedPosition != position){
////            if(isCheked){
////                checkedPosition = position;
////                notifyDataSetChanged();
////            } else {
////                if(!isCheked){
////                    checkedPosition = INVALID_POSITION;
////                    notifyDataSetChanged();
////                }
////            }
////        }
////    }
//}
