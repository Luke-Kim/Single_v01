package com.tacademy.singleplay.data;

import java.io.Serializable;

public class EventNotice implements Serializable {
    private String image;
    private int boardNo;

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getBoardNo() {
        return this.boardNo;
    }

    public void setBoardNo(int boardNo) {
        this.boardNo = boardNo;
    }
}
