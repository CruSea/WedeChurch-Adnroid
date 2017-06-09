package com.gcme.wedechurch;

import android.app.Application;
import android.support.multidex.MultiDex;

/**
 * Created by kzone on 3/6/2017.
 */

public class wedechurch extends Application {

    @Override
    public void onCreate() {
        super.onCreate();



    }


    public static <E extends Enum<E>> String[] getStrArrFromEnum(Class<E> e) {
        Enum<E>[] enumConstants = e.getEnumConstants();
        int numConstants = enumConstants.length;
        String[] strArr = new String[numConstants];
        for (int i = 0; i < numConstants; i++) {
            strArr[i] = enumConstants[i].toString();
        }
        return strArr;
    }



}
