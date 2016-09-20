package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.EventNotice;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class EventNoticeRequest extends AbstractRequest<ResultsList<EventNotice[]>> {
    Request request;

    public EventNoticeRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("boards")
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }


    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<EventNotice[]>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
