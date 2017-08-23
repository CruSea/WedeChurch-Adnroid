package com.gcme.wedechurch.model;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

public class singlechurchevents extends SugarRecord {

    @Unique
    private int mDrawable;
    private String mName;
    private float mRating;


    public singlechurchevents() {

    }

    public singlechurchevents(String name, int drawable, float rating) {
        mName = name;
        mDrawable = drawable;
        mRating = rating;
    }



    public float getRating() {
        return mRating;
    }

    public int getDrawable() {
        return mDrawable;
    }

    public String getName() {
        return mName;
    }
}

