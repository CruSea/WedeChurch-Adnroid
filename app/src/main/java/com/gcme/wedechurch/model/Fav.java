package com.gcme.wedechurch.model;

import android.support.annotation.Keep;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

/**
 * Created by kzone on 2/4/2017.
 */

public class Fav extends SugarRecord {

    @Unique
    private String userId;
    private String churchId;


    public void Fav(){

    }



    public Fav(String userid, String churchid){
        this.userId=userid;
        this.churchId=churchid;
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

    public void setChurchId(String  churchId) {
        this.churchId = churchId;
    }



}
