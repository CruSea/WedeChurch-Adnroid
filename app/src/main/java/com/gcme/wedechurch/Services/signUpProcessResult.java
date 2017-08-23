package com.gcme.wedechurch.Services;

/**
 * Created by Aman on 8/15/17.
 */

public interface signUpProcessResult {
    void onNormalUserSignup();
    void onAgentUserSignup();
    void onProcessError(String error_string);
}
