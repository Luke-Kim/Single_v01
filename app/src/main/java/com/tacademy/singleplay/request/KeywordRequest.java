package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.Keyword;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class KeywordRequest extends AbstractRequest<Keyword> {
    Request request;
    public KeywordRequest(Context context, String action, String keyword) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists=")
                .addPathSegment("action")
                .addPathSegment(action)
                .addPathSegment("keyword=")
                .addPathSegment(keyword)
                .build();
        request = new Request.Builder()
                .tag(context)
                .url(url)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<Keyword>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
