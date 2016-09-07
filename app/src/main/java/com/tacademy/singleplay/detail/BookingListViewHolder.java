package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.MyApplication;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.BookingList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class BookingListViewHolder extends RecyclerView.ViewHolder {
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
    @BindView(R.id.layout_booking_list)
    RelativeLayout posterView;
    @BindView(R.id.image_poster)
    ImageView poster;
//    @Nullable@BindView(R.id.event_image)
//    ImageView imageView;
//    @Nullable@BindView(R.id.event_title)
//    TextView textView;
//    @Nullable @BindView(R.id.cardview_main)
//    CardView cardView;

    public interface OnBookingListItemClickListener {
        public void onBookingListItemClick(View view, BookingList bookingList, int position);
    }

    OnBookingListItemClickListener listener;
    public void setOnBookingListItemClickListener(OnBookingListItemClickListener listener){
        this.listener = listener;

    }

    public BookingListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onBookingListItemClick(view, bookingList, getAdapterPosition());
            }
        });
    }

    BookingList bookingList;
    public void setData(BookingList bookingList) {
        this.bookingList = bookingList;
        placeNameView.setText(bookingList.getPlaceName());
        playDayView.setText(bookingList.getPlayDay());
        playTimeView.setText(bookingList.getPlayTime());
        playNameView.setText(bookingList.getPlayName());
        salePriceView.setText(bookingList.getSalePrice()+"");
        starScoreView.setText(bookingList.getStarScore()+"");
        priceView.setText(bookingList.getPrice()+"");
        Glide.with(MyApplication.getContext())
                .load(bookingList.getPoster())
                .into(poster);
    }
}

