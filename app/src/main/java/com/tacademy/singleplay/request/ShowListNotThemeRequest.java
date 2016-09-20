package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class ShowListNotThemeRequest extends AbstractRequest<ResultsList<ShowList[]>> {
    Request request;

    public ShowListNotThemeRequest(Context context, String action, String sort) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addQueryParameter("action", action)
                .addQueryParameter("sort", sort)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<ShowList[]>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}