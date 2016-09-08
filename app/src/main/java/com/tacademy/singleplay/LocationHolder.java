package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.Search;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-08.
 */
public class LocationHolder extends RecyclerView.ViewHolder{
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

    public interface OnLocationItemClickListener {
        public void onLocationItemClick(View view, Location location, int position);
    }

    OnLocationItemClickListener listener;
    public void setOnLocationItemClickListener(OnLocationItemClickListener listener) {
        this.listener = listener;
    }

    public LocationHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }
//    Location location
}
