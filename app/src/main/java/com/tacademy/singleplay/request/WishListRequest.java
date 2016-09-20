package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.ResultsList;
import com.tacademy.singleplay.data.WishList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class WishListRequest extends AbstractRequest<ResultsList<WishList[]>> {
    Request request;
    public WishListRequest(Context context) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("wishlists")
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<ResultsList<WishList[]>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
