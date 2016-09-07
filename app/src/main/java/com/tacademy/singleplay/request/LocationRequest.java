package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.Location;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class LocationRequest extends AbstractRequest<ArrayList<Location>>{
    Request request;
    public LocationRequest(Context context, String action, String location) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addPathSegment("action=")
                .addPathSegment(action)
                .addPathSegment("location=")
                .addPathSegment(location)
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new  TypeToken<ResultsList<ArrayList<Location>>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
