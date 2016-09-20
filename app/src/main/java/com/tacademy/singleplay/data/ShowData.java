package com.tacademy.singleplay.data;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class ShowData {
    int image;
    String title;

    public void setImage(int image) {
        this.image = image;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage(){
        return this.image;
    }
    public String getTitle(){
        return this.title;
    }

    public ShowData(int image, String title){
        this.image=image;
        this.title=title;
    }
}
