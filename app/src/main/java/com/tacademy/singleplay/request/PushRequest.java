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

    public PushRequest(Context context, String[] day, String[] theme) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .addQueryParameter("action", "push")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("day", day[0])
                .add("day", day[1])
                .add("day", day[2])
                .add("day", day[3])
                .add("day", day[4])
                .add("day", day[5])
                .add("day", day[6])
                .add("theme", theme[0])
                .add("theme", theme[1])
                .add("theme", theme[2])
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
