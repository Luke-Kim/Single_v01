package com.tacademy.singleplay;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by Tacademy on 2016-08-29.
 */
public class MyApplication extends Application {
    static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
    }
    public static Context getContext() {
        return context;
    }
}
