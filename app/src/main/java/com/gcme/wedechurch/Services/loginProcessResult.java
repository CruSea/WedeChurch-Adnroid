package com.gcme.wedechurch.Services;

/**
 * Created by Aman on 8/15/17.
 */

public interface loginProcessResult {
    void onNormalUserLogin();
    void onAgnetUserLogin();
    void onAuthorizedUserLogin();
    void onProcessError(String error_string);
}
