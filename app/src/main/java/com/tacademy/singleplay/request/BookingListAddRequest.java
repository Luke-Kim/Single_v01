package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.BookingListAdd;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class BookingListAddRequest extends AbstractRequest<BookingListAdd> {
    Request request;

    public BookingListAddRequest(Context context) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("reservations")
                .build();
        RequestBody body = new FormBody.Builder()
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<BookingListAdd>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
