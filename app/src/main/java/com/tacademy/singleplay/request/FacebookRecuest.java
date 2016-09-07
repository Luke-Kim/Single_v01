package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.FaceBook;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class FacebookRecuest extends AbstractRequest<ResultsList<FaceBook>> {
    Request request;
    public FacebookRecuest(Context context, String token) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("/auth/facebook/token")
                .build();

        RequestBody body = new FormBody.Builder()
                .add("access_token", token)
                .build();

        request = new Request.Builder()
                .url(url)
                .post(body)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<FaceBook>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
