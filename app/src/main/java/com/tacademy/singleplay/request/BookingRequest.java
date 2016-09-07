package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.Booking;
import com.tacademy.singleplay.data2.ShowDetail;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class BookingRequest extends AbstractRequest<Booking> {
    Request request;
    public BookingRequest(Context context, String userId, String playId, String playName,
                            String usableSeatNo, String seatClass ) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("reservations")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("userId", userId)
                .add("playId", playId)
                .add("playName", playName)
                .add("usableSeatNo", usableSeatNo)
                .add("seatClass", seatClass)
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<Booking>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
