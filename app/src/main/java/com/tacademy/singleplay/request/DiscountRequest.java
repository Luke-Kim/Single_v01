package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.Discount;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class DiscountRequest extends AbstractRequest<ResultsList<Discount>>{
    Request request;

    public DiscountRequest(Context context) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .addPathSegment("discounts")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<Discount>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
