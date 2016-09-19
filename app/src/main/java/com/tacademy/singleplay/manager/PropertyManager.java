package com.tacademy.singleplay.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tacademy.singleplay.MyApplication;

/**
 * Created by Tacademy on 2016-09-19.
 */
public class PropertyManager {
    private static PropertyManager instance;
    public static PropertyManager getInstance() {
        if (instance == null) {
            instance = new PropertyManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_CHECK_LOGIN = "check login";

    public PropertyManager() {
        Context context = MyApplication.getContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setCheckLogin(boolean CheckLogin) {
        mEditor.putBoolean(KEY_CHECK_LOGIN, CheckLogin);
        mEditor.commit();
    }
    public boolean isCheckLogin() {
        return mPrefs.getBoolean(KEY_CHECK_LOGIN, false);
    }

    private static final String PROPERTY_REG_TOKEN = "regtoken";
    public void setRegistrationToken(String token) {
        mEditor.putString(PROPERTY_REG_TOKEN, token);
        mEditor.commit();
    }
    public String getRegistrationToken() {
        return mPrefs.getString(PROPERTY_REG_TOKEN, "");
    }
}
