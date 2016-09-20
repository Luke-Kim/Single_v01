package com.tacademy.singleplay.data;

/**
 * Created by Tacademy on 2016-08-25.
 */
public class InquiryData {
    public int type;
    public String title;
    public int contain_image;


    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return this.type;
    }
    public void setContain_image(int contain_image) {
        this.contain_image = contain_image;
    }

    public int getContain_image() {
        return this.contain_image;
    }

    public InquiryData(int type, String title, int contain_image) {
        this.type = type;
        this.title = title;
        this.contain_image = contain_image;
    }

    public InquiryData() {

    }
}
