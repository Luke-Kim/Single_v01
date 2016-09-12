package com.tacademy.singleplay.manager;

/**
 * Created by heesung on 2016-09-12.
 */
public class ShowListManager {
    private String action;
    private String sort;
    private int position;

    private static ShowListManager instance;
    public static ShowListManager getInstance() {
        if (instance == null) {
            instance = new ShowListManager();
        }
        return instance;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
