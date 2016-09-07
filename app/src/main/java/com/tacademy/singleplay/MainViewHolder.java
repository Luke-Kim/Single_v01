package com.tacademy.singleplay;

import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.singleplay.data.ShowData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class MainViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.event_image)
    ImageView imageView;
    @BindView(R.id.event_title)
    TextView textView;
    @Nullable @BindView(R.id.cardview_main)
    CardView cardView;

    // VIewHolder에서 item이 눌리면 알고싶다. 리스너 정의 한다.
    public interface OnShowItemClickListener { // 1.interface 정의
        public void onShowItemClick(View view, ShowData showData, int position);
        //callBack 함수, 첫번째 인자:눌린 view 넘김, 두번째:ShowData 넘김, 세번째:position 값 넘김
    }

    OnShowItemClickListener listener;
    public void setOnShowItemClickListener(OnShowItemClickListener listener) { //리스너 등록함수
        this.listener = listener;
    }

    public MainViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() { // itemView가 눌리면 클릭 된 것이다.
            @Override
            public void onClick(View view) {
                listener.onShowItemClick(view, showData, getAdapterPosition());
                // onShowItemClick함수의 아규먼트 int position과 getAdapterPosition -> 이걸로 어댑터 상의 position 넘겨온다!
                //adatper상의 position은 data의 position이다.
            }
        });
        // view들은 'itemViewf.'으로 찾아온다. ViewHolder안에 itemView가 있다.
        //image = (ImageView)itemView.findViewById(R.id.event_image);
        //title = (TextView)itemView.findViewById(R.id.event_title);
        //cardview = (CardView)itemView.findViewById(R.id.cardview_main);
    }

    ShowData showData;
    public void setData(ShowData showData) {
        this.showData = showData;
        imageView.setImageResource(showData.getImage());
        textView.setText(showData.getTitle());
    }
}