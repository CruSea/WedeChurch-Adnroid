package com.gcme.wedechurch.Services;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import kotlin.Pair;

/**
 * Created by bengeos on 8/13/17.
 */

public class SendRequestService {
    private List<Pair<String,String>> SendParam;
    private String API_URL;

    public SendRequestService(List<Pair<String, String>> sendParam, String API_URL) {
        SendParam = sendParam;
        this.API_URL = API_URL;
    }
    public void processRequest(final RequestCallback callback){
        try{
            Fuel.post(API_URL,SendParam).responseString(new Handler<String>() {
                @Override
                public void success(@NotNull Request request, @NotNull Response response, String s) {
                    callback.successCallback(s);
                }

                @Override
                public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {
                    callback.failedCallback(fuelError.toString());
                }
            });
        }catch (Exception e){
            callback.failedCallback("SendRequest:\n"+e.toString());
            callback.failedCallback(e.toString());
        }
    }
}
