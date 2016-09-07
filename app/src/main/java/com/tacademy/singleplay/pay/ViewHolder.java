package com.tacademy.singleplay.pay;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.ShowData;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.event_image)
    ImageView imageView;
    @BindView(R.id.event_title)
    TextView textView;
//    @Nullable @BindView(R.id.cardview)
//    CardView cardView;

    //public ImageView image;
    //public TextView title;
    //public CardView cardview;

    public ViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        //image = (ImageView)itemView.findViewById(R.id.event_image);
        //title = (TextView)itemView.findViewById(R.id.event_title);
        //cardview = (CardView)itemView.findViewById(R.id.cardview);
    }

    ShowData showData;
    public void setData(ShowData showData) {
        this.showData = showData;
        imageView.setImageResource(showData.getImage());
        textView.setText(showData.getTitle());


    }


}