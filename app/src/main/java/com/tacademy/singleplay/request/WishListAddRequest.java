package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class WishListAddRequest extends AbstractRequest<ResultsList<String[]>> {
    Request request;
    public WishListAddRequest(Context context, String playId) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("wishlists")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("playId", playId)
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }
    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<String[]>> (){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
