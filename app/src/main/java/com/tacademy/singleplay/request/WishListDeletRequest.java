package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.WishListDelete;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class WishListDeletRequest extends AbstractRequest<WishListDelete> {
    Request request;
    public WishListDeletRequest(Context context, String wid) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("wishlists")
                .addPathSegment(wid)
                .build();
        request = new Request.Builder()
                .url(url)
                .delete()
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<WishListDelete>(){}.getType();    }

    @Override
    public Request getRequest() {
        return request;
    }
}
