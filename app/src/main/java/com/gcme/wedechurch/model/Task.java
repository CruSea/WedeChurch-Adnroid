package com.gcme.wedechurch.model;

import android.support.annotation.Keep;

import com.orm.SugarRecord;

/**
 * Created by kzone on 2/4/2017.
 */

@Keep
public class Task extends SugarRecord<Task> {

    public int gettasksirId() {
        return tasksirId;
    }

    public void setId(int id) {
        this.tasksirId = id;
    }

    private int tasksirId;


    public Task(){

    }


}
