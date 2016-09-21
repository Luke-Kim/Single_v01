package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.Booking;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowDetail;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class BookingRequest extends AbstractRequest<ResultsList<Booking>> {
    Request request;

    public BookingRequest(Context context, String playId, String playName,
                          String usableSeatNo, String seatClass, String booker,
                          String bookerPhone, String bookerEmail, String useCoupon,
                          String useMileage, String settlement) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("reservations")
                .build();
        RequestBody body = new FormBody.Builder()
                .add("playId", playId)
                .add("playName", playName)
                .add("usableSeatNo", usableSeatNo)
                .add("seatClass", seatClass)
                .add("booker", booker)
                .add("bookerPhone", bookerPhone)
                .add("bookerEmail", bookerEmail)
                .add("useCoupon", useCoupon)
                .add("useMileage", useMileage)
                .add("settlement", settlement)
                .build();
        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<Booking>>() {
        }.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
