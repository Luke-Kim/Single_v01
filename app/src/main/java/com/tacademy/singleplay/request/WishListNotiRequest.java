package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.WishListNoti;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class WishListNotiRequest extends AbstractRequest<WishListNoti> {
    Request request;
    public WishListNotiRequest(Context context, String noti) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .addQueryParameter("action", "wish")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("noti", noti)
                .build();

        request = new Request.Builder()
                .url(url)
                .put(body) //PUT 방식이니까 post 대신 put!
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<WishListNoti>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
