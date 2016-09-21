package com.tacademy.singleplay;

import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.data2.ShowList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainShowHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.text_place_name)
    TextView placeNameView;
    @BindView(R.id.text_play_day)
    TextView playDayView;
    @BindView(R.id.text_play_time)
    TextView playTimeView;
    @BindView(R.id.text_play_name)
    TextView playNameView;
    @BindView(R.id.text_sale_price)
    TextView salePriceView;
    @BindView(R.id.text_star_score)
    TextView starScoreView;
    @BindView(R.id.text_price)
    TextView priceView;
    @BindView(R.id.layout_show_list)
    RelativeLayout posterView;
    @BindView(R.id.image_poster)
    ImageView poster;
    @BindView(R.id.show_list_layout)
    RelativeLayout show_layout;



    public interface OnShowItemClickListener {
        public void onShowItemClick(View view, ShowList showList, int position);
    }

    OnShowItemClickListener listener;
    public void setOnShowItemClickListener(OnShowItemClickListener listener) {
        this.listener = listener;
    }

    public MainShowHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() { // itemView가 눌리면 클릭 된 것이다.
            @Override
            public void onClick(View view) {
                listener.onShowItemClick(view, showList, getAdapterPosition());
            }
        });
    }

    ShowList showList;
    public void setData(ShowList showList) {
        this.showList = showList;
        placeNameView.setText(showList.getPlaceName());
        playDayView.setText(showList.getPlayDay());
        playTimeView.setText(showList.getPlayTime());
        playNameView.setText(showList.getPlayName());
        salePriceView.setText(showList.getSalePrice()+"");
        starScoreView.setText(showList.getStarScore()+"");
        priceView.setText(showList.getPrice()+"");
        priceView.setPaintFlags(priceView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG); // 취소선
        Glide.with(MyApplication.getContext())
                .load(showList.getPoster())
                .into(poster);
        poster.setBackgroundResource(R.drawable.gradation);
//        Drawable postor;
//        postor = poster.getDrawable();
//        posterView.setBackground(postor);
//        posterView.setBackground();
    }
}
