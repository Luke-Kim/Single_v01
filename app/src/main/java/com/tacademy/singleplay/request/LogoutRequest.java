package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.Logout;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class LogoutRequest extends AbstractRequest<ResultsList<Logout>> {
    Request request;
    public LogoutRequest(Context context) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("auth")
                .addPathSegment("logout")
                .build();


        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<Logout>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
