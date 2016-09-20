package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.ResultsList;
import com.tacademy.singleplay.data.ShowDetail;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class ShowDetailReqest extends AbstractRequest<ResultsList<ShowDetail>> {
    Request request;

    public ShowDetailReqest(Context context, String pid) {
        HttpUrl url = getBaseUrlBuilder()
                .addPathSegment("playlists")
                .addPathSegment(pid)
                .build();
        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<ShowDetail>>(){}.getType();    }

    @Override
    public Request getRequest() {
        return request;
    }
}
