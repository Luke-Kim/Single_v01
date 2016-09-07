package com.tacademy.singleplay.manager;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.tacademy.singleplay.MyApplication;


/**
 * Created by Tacademy on 2016-08-10.
 */
public class BookingManager {
    private static BookingManager instance;
    public static BookingManager getInstance() {
        if (instance == null) {
            instance = new BookingManager();
        }
        return instance;
    }

    SharedPreferences mPrefs;
    SharedPreferences.Editor mEditor;

    private static final String KEY_PLAY_ID = "play id";
    private static final String KEY_PLAY_NAME = "play name";
    private static final String KEY_SELECT_SEAT = "selcet seat";
    private static final String KEY_SEAT_CLASS = "seat class";

    private BookingManager() {
        Context context = MyApplication.getContext();
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        mEditor = mPrefs.edit();
    }

    public void setPlayId(String id) {
        mEditor.putString(KEY_PLAY_ID, id);
        mEditor.commit();
    }

    public String getPlayId() {
        return mPrefs.getString(KEY_PLAY_ID, "");
    }

    public void setPlayName(String name) {
        mEditor.putString(KEY_PLAY_NAME, name);
        mEditor.commit();
    }

    public String getPlayName() {
        return mPrefs.getString(KEY_PLAY_NAME, "");
    }

    public void setUsableSeat(String seat) {
        mEditor.putString(KEY_SELECT_SEAT, seat);
        mEditor.commit();
    }

    public String getUsableSeat() {
        return mPrefs.getString(KEY_SELECT_SEAT, "");
    }

    public void setSeatClass(String seatClass) {
        mEditor.putString(KEY_SEAT_CLASS, seatClass);
        mEditor.commit();
    }

    public String getSeatClass() {
        return mPrefs.getString(KEY_SEAT_CLASS, "");
    }
}
