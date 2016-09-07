package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.BookingDetail;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class BookingDetailRequest extends AbstractRequest<ResultsList<BookingDetail>> {
    Request request;
    public BookingDetailRequest(Context context, String rid) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("reservations")
                .addPathSegment(rid)
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<ResultsList<BookingDetail>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
