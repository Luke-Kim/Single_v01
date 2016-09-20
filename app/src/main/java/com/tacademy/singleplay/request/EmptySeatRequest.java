package com.tacademy.singleplay.request;

import android.content.Context;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data.EmptySeat;
import com.tacademy.singleplay.data.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Tacademy on 2016-09-06.
 */
public class EmptySeatRequest extends AbstractRequest<ResultsList<EmptySeat>> {
    Request request;

    public EmptySeatRequest(Context context, String pid) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("usableseats")
                .addPathSegment(pid)
                .build();

        request = new Request.Builder()
                .url(url)
                .tag(context)
                .build();
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<EmptySeat>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
