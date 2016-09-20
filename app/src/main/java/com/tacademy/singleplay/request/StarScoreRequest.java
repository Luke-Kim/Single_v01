package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.StarScore;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class StarScoreRequest extends AbstractRequest<StarScore> {
    Request request;
    public StarScoreRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("reviews")
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
        return new TypeToken<StarScore>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
