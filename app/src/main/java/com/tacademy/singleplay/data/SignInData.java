package com.tacademy.singleplay.data;

import java.io.Serializable;

/**
 * Created by Tacademy on 2016-08-26.
 */
public class SignInData implements Serializable {
    public String name;
    public String phone;

    public SignInData() {}

    public SignInData(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
