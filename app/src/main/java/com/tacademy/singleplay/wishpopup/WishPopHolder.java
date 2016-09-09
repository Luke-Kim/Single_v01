package com.tacademy.singleplay.wishpopup;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Tacademy on 2016-09-05.
 */
public class WishPopHolder extends RecyclerView.ViewHolder {
    @Nullable
    @BindView(R.id.wish_img)
    ImageView wish_img;


    public WishPopHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);

    }

//    WishListAdd wishLiastadd;
//    public void setData(WishListAdd wishLiastadd){
//        this.wishLiastadd = wishLiastadd;
//        Glide.with(wish_img.getContext())
//                .load(wishLiastadd.getThumbnail()) // string
//                .into(wish_img);
//    }

    public void setData(String wishLiastadd){
        Glide.with(wish_img.getContext())
                .load(wishLiastadd) // string
                .into(wish_img);
    }

}
