package com.tacademy.singleplay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.data.WishList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class WishListViewHolder extends RecyclerView.ViewHolder {
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
    @BindView(R.id.layout_wish_list)
    RelativeLayout posterView;
    @BindView(R.id.image_poster)
    ImageView poster;

    public interface OnWishListItemClickListener {
        public void onWishListItemClick(View view, WishList wishList, int position);
    }

    OnWishListItemClickListener listener;
    public void setOnWishListItemClickListener(OnWishListItemClickListener listener) {
        this.listener = listener;
    }

    public WishListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView); // itemView에서 찾아서 this에 맵핑하겠다는 뜻이다!!
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onWishListItemClick(view, wishList, getAdapterPosition());
            }
        });
    }

    WishList wishList;
    public void setData(WishList wishList) {
        this.wishList = wishList;
        placeNameView.setText(wishList.getPlaceName());
        playDayView.setText(wishList.getPlayDay());
        playTimeView.setText(wishList.getPlayTime());
        playNameView.setText(wishList.getPlayName());
        salePriceView.setText(wishList.getSalePrice()+"");
        starScoreView.setText(wishList.getStarScore()+"");
        priceView.setText(wishList.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(wishList.getPoster())
                .into(poster);
    }
}
