package com.gcme.wedechurch.PrefManagers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by bengeos on 7/23/17.
 */

public class IntroPrefManager {
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context myContext;

    int PRIVATE_MODE = 0;
    private static final String PREFERENCE_NAME = "HuluSport-welcome";

    private static final String IS_FIRST_TIME_LAUNCH = "1";

    public IntroPrefManager(Context myContext) {
        this.myContext = myContext;
        pref = myContext.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }
    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }
    public boolean isFirstTimeLaunch() {
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
