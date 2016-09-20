package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.Location;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class LocationRequest extends AbstractRequest<ResultsList<Location[]>>{
    Request request;
    public LocationRequest(Context context, String action, String location) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addQueryParameter("action", action)
                .addQueryParameter("location", location)
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<ResultsList<Location[]>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
