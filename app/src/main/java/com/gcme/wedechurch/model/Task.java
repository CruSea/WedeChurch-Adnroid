package com.gcme.wedechurch.model;

import android.support.annotation.Keep;
import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;

/**
 * Created by kzone on 2/4/2017.
 */

public class Task extends SugarRecord {

    @Unique
    private int tasksirId;

    public Task() {

    }

    public int gettasksirId() {
        return tasksirId;
    }
    public void setId(int id) {
        this.tasksirId = id;
    }




    public Task(int taskId){
        tasksirId=taskId;
    }


}
