package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.ResultsList;
import com.tacademy.singleplay.data2.ShowList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
//?action=0&theme=0&sort=0&start=20
public class ShowListRequest extends AbstractRequest<ShowList> {
    Request request;

    public ShowListRequest(Context context, String action, String theme, String sort) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addQueryParameter("action", action)
                .addQueryParameter("theme", theme)
                .addQueryParameter("sort", sort)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ShowList>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}


