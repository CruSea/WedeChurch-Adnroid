package com.gcme.wedechurch.Objects;

import android.support.annotation.Keep;

/**
 * Created by kzone on 2/4/2017.
 */

@Keep
public class Fav {
    private int id;
    private String userId;
    private String churchId;


    public Fav(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChurchId() {
        return churchId;
    }

    public void setChurchId(String churchId) {
        this.churchId = churchId;
    }






}
