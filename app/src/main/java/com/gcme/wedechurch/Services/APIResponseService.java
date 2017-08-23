package com.gcme.wedechurch.Services;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Aman on 8/15/17.
 */

public class APIResponseService {
    public void processLoginResponse(String jsonResponse, loginProcessResult loginProcessResult){
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if(!jsonObject.isNull("authorized")){
                if(jsonObject.getString("authorized").equals("1")){
                    loginProcessResult.onNormalUserLogin();
                }else if(jsonObject.getString("authorized").equals("2")){
                    loginProcessResult.onAgnetUserLogin();
                }else {
                    loginProcessResult.onAuthorizedUserLogin();
                }
            }else {
                loginProcessResult.onAuthorizedUserLogin();
            }
        } catch (JSONException e) {
            e.printStackTrace();
            loginProcessResult.onProcessError(e.toString());
        }
    }
    public void processSignupResponse(String jsonResponse, signUpProcessResult signUpProcessResult){
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if(!jsonObject.isNull("authorized") && !jsonObject.isNull("completed") ){
//                if(jsonObject.getString("authorized").equals("1")){
                    signUpProcessResult.onNormalUserSignup();
//                }else if(jsonObject.getString("authorized").equals("2")){
//                    signUpProcessResult.onAgentUserSignup();
//                }else {
//                    signUpProcessResult.onProcessError("Failed to register the user");
//                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            signUpProcessResult.onProcessError(e.toString());
        }
    }

    /*
    public void processBetPlacedResponse(String jsonResponse, BetPlaceEvent betPlaceEvent){
        try {
            JSONObject jsonObject = new JSONObject(jsonResponse);
            if(!jsonObject.isNull("completed")){
                huluPlacedBet huluPlacedBet = new huluPlacedBet();
                huluPlacedBet.setId(Long.valueOf(jsonObject.getString("slipID")));
                betPlaceEvent.onSuccessfullyPlaced(huluPlacedBet);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            betPlaceEvent.onFailedPlaced();
        }
    }
    public void processGetNewBetEvents(String json_array, NewBetGameEvents newBetGameEvents){
        try {
            JSONArray jsonArray = new JSONArray(json_array);
            if(jsonArray.length()>0){
                for(int i=0;i<jsonArray.length();i++){
                    boolean already_there = false;
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    huluBetObject newHuluBetObject = new huluBetObject();
                    newHuluBetObject.setSer_id(jsonObject.getString("id"));
                    newHuluBetObject.setEvent_id(jsonObject.getString("event_id"));
                    newHuluBetObject.setParent(jsonObject.getString("parent"));
                    newHuluBetObject.setEvent_type(jsonObject.getString("event_type"));
                    newHuluBetObject.setTeamA(jsonObject.getString("teamA"));
                    newHuluBetObject.setTeamB(jsonObject.getString("teamB"));
                    newHuluBetObject.setGame_type(jsonObject.getString("game_type"));
                    newHuluBetObject.setGame_date(jsonObject.getString("game_date"));
                    newHuluBetObject.setHome_odd(jsonObject.getJSONObject("picks").getJSONObject("HOME").getString("odd"));
                    newHuluBetObject.setAway_odd(jsonObject.getJSONObject("picks").getJSONObject("AWAY").getString("odd"));
                    newHuluBetObject.setDraw_odd(jsonObject.getJSONObject("picks").getJSONObject("DRAW").getString("odd"));

                    for (huluBetObject huluBetObject: com.sport.hulu.hulusport.ObjectModels.huluBetObject.listAll(com.sport.hulu.hulusport.ObjectModels.huluBetObject.class)) {
                        if(huluBetObject.getSer_id().equals(newHuluBetObject.getSer_id())){
                            already_there = true;
                            newHuluBetObject.setId(huluBetObject.getId());
                            break;
                        }
                    }
                    if(already_there){
                        newHuluBetObject.update();
                    }else {
                        newHuluBetObject.save();
                    }
                }
                newBetGameEvents.onBetUpdated();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/
}
