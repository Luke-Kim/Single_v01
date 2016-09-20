package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
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
public class PushRequest extends AbstractRequest<Push> {
    Request request;

    public PushRequest(Context context, String noti, String[] day, String[] theme) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .addQueryParameter("action", "push")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("noti", noti)
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

//        RequestBody body = new FormBody.Builder()
//                .add("noti", "on")
//                .add("day", "1")
//                .add("day", "1")
//                .add("day", "1")
//                .add("day", "1")
//                .add("day", "1")
//                .add("day", "1")
//                .add("day", "1")
//                .add("theme", "1")
//                .add("theme", "1")
//                .add("theme", "1")
//                .build();

        request = new Request.Builder()
                .url(url)
                .put(body) //PUT 방식이니까 post 대신 put!
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<Push>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
