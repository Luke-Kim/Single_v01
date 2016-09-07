package com.tacademy.singleplay.detail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data2.EventNotice;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-01.
 */
public class EventNoticeViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.image_thumbnail)
    ImageView imageView;
    @BindView(R.id.txt_list)
    TextView textView;

    EventNotice eventNotice;


    public interface OnEventNoticeItemClickListener {
        public void onEventNoticeItemClick(View view, EventNotice eventNotice, int position);
    }

    OnEventNoticeItemClickListener listener;

    public void setOnEventNoticeItemClickListener(final EventNoticeAdapter listener) {
        this.listener = listener;
    }

    public EventNoticeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onEventNoticeItemClick(view, eventNotice, getAdapterPosition());
            }
        });
    }

    public void setData(EventNotice eventNotice) {
        this.eventNotice = eventNotice;
        Glide.with(imageView.getContext())
                .load(eventNotice.getImage())
                .into(imageView);
        textView.setText(eventNotice.getBoardNo()+"");
    }
}



