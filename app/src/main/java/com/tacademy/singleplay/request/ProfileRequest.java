package com.tacademy.singleplay.request;

import android.content.Context;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.tacademy.singleplay.data2.Profile;
import com.tacademy.singleplay.data2.ResultsList;

import java.lang.reflect.Type;

import okhttp3.HttpUrl;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Tacademy on 2016-08-30.
 */
public class ProfileRequest extends AbstractRequest<ResultsList<Profile>> {
    Request request;

    public ProfileRequest(Context context, String userName, String userImage, String userEmail, String userPhone) {
        HttpUrl url = getBaseUrlHttpsBuilder()
                .addPathSegment("users")
                .addPathSegment("me")
                .addQueryParameter("action","profile") //todo 이거 문승필이 데려와 from 전찬웅
                .build();

        MultipartBody.Builder body = new MultipartBody.Builder()  //todo 사진을 파일로 서버에 올리려면 MultipartBody 써야한다!
//                .addFormDataPart("action", "profile")
                .addFormDataPart("userName", userName)
                .addFormDataPart("userImage", userImage)
                .addFormDataPart("userEmail", userEmail)
                .addFormDataPart("userPhone", userPhone);

        RequestBody requestbody= body.build();


        request = new Request.Builder()
                .url(url)
                .put(requestbody)
                .tag(context)
                .build();
        Log.i("url",url.toString());
    }

    @Override
    protected Type getType() {
        return new TypeToken<ResultsList<Profile>>(){}.getType();
    }

    @Override
    public Request getRequest() {
        return request;
    }
}
