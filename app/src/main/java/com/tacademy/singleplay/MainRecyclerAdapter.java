package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tacademy.singleplay.data.ShowData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class MainRecyclerAdapter extends RecyclerView.Adapter<MainViewHolder> implements MainViewHolder.OnShowItemClickListener { // ViewHolder에서 만든 리스너 등록해준다.(implements)

    List<ShowData> items = new ArrayList<>();


    public void add(ShowData showData) {
        items.add(showData);
        notifyDataSetChanged();
    }

    public MainRecyclerAdapter() {

    }

//    public MainRecyclerAdapter(Context context, List<ShowData> items, int item_layout) {
//        this.context = context;
//        this.items = items;
//        this.item_layout = item_layout;
//    }

    @Override
    public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) { // 이 메서드 안의 내용을 잘 바꾸어야 내가 원하는 결과물이 나온다.
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_main_show_list, null); //ViewHolder에 넣어줄 view를 찾는 과정.
        MainViewHolder vh = new MainViewHolder(v);
        vh.setOnShowItemClickListener(this); // 생성되었을 때, 리스너 등록한다!!
        return vh;
    }

    @Override
    public void onBindViewHolder(MainViewHolder holder, int position) { // RecyclerView의 item의 셋팅과 사용자가 스크롤링 할때, 호출되는 메서드.
        // 내가 원하는 데이터가 포지션 별로 ArrayList<Mydata>에 저장되어 있으면 이러한 데이터를
        // 포지션 별로 보여주는 것을 보장해준다.
        holder.setData(items.get(position));
        //holder.setOnShowItemClickListener(this);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    // ViewHolder에서 했던것 처럼 리스너 만든다.
    public interface OnShowAdapterItemClickLIstener {
        public void onShowAdapterItemClick(View view, ShowData showData, int position);
    }

    OnShowAdapterItemClickLIstener listener;
    public void setOnAdapterItemClickListener(OnShowAdapterItemClickLIstener listener) {
        this.listener = listener;
    }

    @Override
    public void onShowItemClick(View view, ShowData showData, int position) {
        if (listener != null) {
            listener.onShowAdapterItemClick(view, showData, position);
        }

    }

}