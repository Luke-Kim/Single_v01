package com.tacademy.singleplay.wishpopup;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.tacademy.singleplay.R;
import com.tacademy.singleplay.data.WishListAdd;

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

    WishListAdd wishListAdd = new WishListAdd();
    public void setData(String wishListAdd){
        this.wishListAdd.setThumbnail(wishListAdd);
        Log.i("wish",this.wishListAdd.getThumbnail());
        Glide.with(wish_img.getContext())
                .load(this.wishListAdd.getThumbnail()) // string
                .into(wish_img);

    }
}
