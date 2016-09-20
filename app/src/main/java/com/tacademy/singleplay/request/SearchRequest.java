package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.ResultsList;
import com.tacademy.singleplay.data.Search;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-07.
 */
public class SearchRequest extends AbstractRequest<ResultsList<Search[]>> {
    Request request;

    public SearchRequest(Context context, String action, String keyword) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addQueryParameter("action", action)
                .addQueryParameter("keyword", keyword)
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<Search[]>>() {}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
