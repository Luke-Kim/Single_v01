package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.data2.Search;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class SearchHolder extends RecyclerView.ViewHolder {

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


    public interface OnSearchItemClickListener {
        public void onSearchItemClick(View view, Search search, int position);
    }

    OnSearchItemClickListener listener;
    public void setOnSearchItemClickListener(OnSearchItemClickListener listener) {
        this.listener = listener;
    }

    public SearchHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSearchItemClick(view, search, getAdapterPosition());
            }
        });
    }

    Search search;
    public void setData(Search search) {
        this.search = search;
        placeNameView.setText(search.getPlaceName());
        playDayView.setText(search.getPlayDay());
        playTimeView.setText(search.getPlayTime());
        playNameView.setText(search.getPlayName());
        salePriceView.setText(search.getSalePrice()+"");
        starScoreView.setText(search.getStarScore()+"");
        priceView.setText(search.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(search.getPoster())
                .into(poster);
    }
}
