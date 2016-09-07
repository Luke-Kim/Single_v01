package com.tacademy.singleplay.data;

/**
 * Created by Tacademy on 2016-08-24.
 */
public class EventData {
    private int photo;
    String title;

    public EventData(){

    }
    public EventData(int photo, String title){
        this.photo=photo;
        this.title=title;
    }



    public int getImage(){
        return this.photo;
    }
    public String getTitle(){
        return this.title;
    }
    public int setPhoto(int photo) {
        return this.photo;
    }
    public String setTitle() {
        return this.title;
    }


}
