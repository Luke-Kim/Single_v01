package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.EventNoticeDetail;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class EventNoticeDetailRequest extends AbstractRequest<ResultsList<EventNoticeDetail>> {
    Request request;

    public EventNoticeDetailRequest(Context context, String bid) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .addPathSegment(bid)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<EventNoticeDetail>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
