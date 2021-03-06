package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.BookingCancel;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class BookingCancelRequest extends AbstractRequest<BookingCancel> {
    Request request;
    public BookingCancelRequest(Context context, String rid) {
            HttpUrl url = getBaseUrlHttpsBuilder()
                    .addPathSegment("reservations")
                    .addPathSegment(rid)
                    .build();
        RequestBody body = new FormBody.Builder()
                .build();
            request = new Request.Builder()
                    .url(url)
                    .put(body)
                    .tag(context)
                    .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<BookingCancel>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
