package com.gcme.wedechurch.Services;

/**
 * Created by bengeos on 8/13/17.
 */

public interface RequestCallback {
    void successCallback(String string);
    void failedCallback(String string);
}
