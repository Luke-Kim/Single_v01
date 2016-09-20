package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.BookingList;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class BookingListRequest extends AbstractRequest<ResultsList<BookingList[]>> {
    Request request;
    public BookingListRequest(Context context) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("reservations")
                .build();
        request = new Request.Builder()
                .tag(context)
                .url(url)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<ResultsList<BookingList[]>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
