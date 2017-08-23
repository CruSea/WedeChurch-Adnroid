package com.gcme.wedechurch.PrefManagers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Aman on 8/15/17.
 */

public class UserPrefManager {
    int PRIVATE_MODE = 0;
    private static final String PREFERENCE_NAME = "HuluSport-User";
    private static final String USER_TYPE = "user_type";
    private static final String USER_LANGUAGE = "user_language";
    private static final String USER_BET_DETAIL = "user_bet_detail";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context myContext;

    public UserPrefManager(Context myContext) {
        this.myContext = myContext;
        pref = this.myContext.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void setUserTypeAsNormalUser(boolean userType){
        editor.putBoolean(USER_TYPE, userType);
        editor.commit();
    }
    public boolean isNormalUser(){
        return pref.getBoolean(USER_TYPE,true);
    }

    public void setUserLanguageEnglish(boolean userLanguageEnglish){
        editor.putBoolean(USER_LANGUAGE, userLanguageEnglish);
        editor.commit();
    }
    public boolean isUserLanguageEnglish(){
        return pref.getBoolean(USER_LANGUAGE,false);
    }

    public void setDetailID(long num){
        editor.putLong(USER_BET_DETAIL, num);
        editor.commit();
    }
    public long getDetailID(){
        return pref.getLong(USER_BET_DETAIL,0);
    }
}
