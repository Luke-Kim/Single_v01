package com.tacademy.singleplay.manager;

/**
 * Created by Tacademy on 2016-09-21.
 */
public class ReviewManager {
    private String playId;
    private String playName;
    private String theme;

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    private static ReviewManager instance;
    public static ReviewManager getInstance() {
        if (instance == null) {
            instance = new ReviewManager();
        }
        return instance;
    }

    public String getPlayId() {
        return playId;
    }

    public void setPlayId(String playId) {
        this.playId = playId;
    }

    public String getPlayName() {
        return playName;
    }

    public void setPlayName(String playName) {
        this.playName = playName;
    }
}
