package com.tacademy.singleplay.request;

import android.content.Context;

import com.tacademy.singleplay.data2.Push;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class PushRequest extends AbstractRequest<ResultsList<Push>> {
    Request request;

    public PushRequest(Context context, String day, String theme) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("/users/me")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("action","push")
                .add("day", day)
                .add("theme", theme)
                .build();

        request = new Request.Builder()
                .url(url)
                .put(body) //PUT 방식이니까 post 대신 put!
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return null;
    }

    @Override
    public Request getRequest() {
        return null;
    }
}
