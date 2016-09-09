package com.tacademy.singleplay.request;

import com.google.gson.Gson;
import com.tacademy.singleplay.manager.NetworkRequest;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.ResponseBody;

/**
 * Created by heesung on 2016-08-09.
 */
public abstract class AbstractRequest<T> extends NetworkRequest<T> {

    private final static String HOST = "ec2-52-78-118-8.ap-northeast-2.compute.amazonaws.com";
    private final static int HTTPS_PORT = 4433;
    private final static int HTTP_PORT=8080;

    protected HttpUrl.Builder getBaseUrlBuilder() { //각 Request들이 생성자에서 호출하여 builder를 받아감.
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("http"); //스킴 설정
        builder.host(HOST); //호스트 설정
        builder.port(HTTP_PORT);
        return builder;
    }

    protected HttpUrl.Builder getBaseUrlHttpsBuilder() { //각 Request들이 생성자에서 호출하여 builder를 받아감.
        HttpUrl.Builder builder = new HttpUrl.Builder();
        builder.scheme("https"); //스킴 설정
        builder.host(HOST);
        builder.port(HTTPS_PORT);//호스트 설정
        return builder;
    }

    @Override
    protected T parse(ResponseBody body) throws IOException {
        String text = body.string();
        Gson gson = new Gson();
        T temp = gson.fromJson(text, getType());
        return temp;
    }

    protected abstract Type getType();
}