package com.gcme.wedechurch.model;

import android.support.annotation.Keep;

import com.orm.SugarRecord;

/**
 * Created by kzone on 2/4/2017.
 */

@Keep
public class Fav extends SugarRecord<Fav> {

    private int favId;
    private String userId;
    private String churchId;

    public int getfavId() {
        return favId;
    }

    public void setId(int id) {
        this.favId = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getChurchId() {
        return churchId;
    }

    public void setChurchId(String churchId) {
        this.churchId = churchId;
    }

}
