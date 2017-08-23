package com.gcme.wedechurch.app;

import android.app.Application;
        import android.content.Context;
        import android.content.SharedPreferences;
        import android.location.Address;
        import android.location.Geocoder;
        import android.net.ConnectivityManager;
        import android.net.NetworkInfo;
        import android.support.multidex.MultiDex;
        import android.text.TextUtils;
        import android.util.Log;
        import android.widget.Toast;

        import com.android.volley.DefaultRetryPolicy;
        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.RetryPolicy;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.ImageLoader;
        import com.android.volley.toolbox.Volley;


        import com.gcme.wedechurch.R;
        import com.gcme.wedechurch.Services.RequestServices;
        import com.gcme.wedechurch.constants.Constants;
        import com.gcme.wedechurch.util.CustomRequest;
        import com.gcme.wedechurch.util.GPSTracker;
        import com.gcme.wedechurch.util.LruBitmapCache;
        import com.orm.SugarApp;
        import com.orm.SugarContext;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Locale;
        import java.util.Map;

        import static com.gcme.wedechurch.constants.Constants.CLIENT_ID;
        import static com.gcme.wedechurch.constants.Constants.METHOD_ACCOUNT_LOGOUT;
        import static com.gcme.wedechurch.constants.Constants.METHOD_ACCOUNT_SET_GEO_LOCATION;

public class App extends com.orm.SugarApp {
    public static final int HOME_FRAGMENT = 1;
    public static final int AGENTS_FRAGMENT = 2;
    private RequestServices myRequestServices;
    GPSTracker gps;

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());

    }



//    public void getLocation() {
//
//        if (App.getInstance().isConnected()) {
//
//            gps = new GPSTracker(this);
//
//            if (gps.canGetLocation()) {
//
//                final double latitude = gps.getLatitude();
//                final double longitude = gps.getLongitude();
//
//                if (App.getInstance().isConnected() && App.getInstance().getId() != 0) {
//
//                    CustomRequest jsonReq = new CustomRequest(Request.Method.POST, METHOD_ACCOUNT_SET_GEO_LOCATION, null,
//                            new Response.Listener<JSONObject>() {
//                                @Override
//                                public void onResponse(JSONObject response) {
//
//                                    try {
//
//                                        if (!response.getBoolean("error")) {
//
////                                            Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
//                                        }
//
//                                    } catch (JSONException e) {
//
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//
//                            //Toast.makeText(getApplicationContext(), getString(R.string.msg_network_error), Toast.LENGTH_SHORT).show();
//                        }
//                    }) {
//
//                        @Override
//                        protected Map<String, String> getParams() {
//                            Map<String, String> params = new HashMap<String, String>();
//                            params.put("accountId", Long.toString(App.getInstance().getId()));
//                            params.put("accessToken", App.getInstance().getAccessToken());
//                            params.put("lat", Double.toString(latitude));
//                            params.put("lng", Double.toString(longitude));
//
//                            return params;
//                        }
//                    };
//
//                    App.getInstance().addToRequestQueue(jsonReq);
//                }
//
//                Geocoder geocoder;
//                List<Address> addresses;
//                geocoder = new Geocoder(getApplicationContext(), Locale.ENGLISH);
//
//                try {
//
//                    addresses = geocoder.getFromLocation(latitude, longitude, 1);
//
//                    if (addresses.size() > 0) {
//
//                        App.getInstance().setLat(latitude);
//                        App.getInstance().setLng(longitude);
//
//                        App.getInstance().setArea(addresses.get(0).getAdminArea());
//                        App.getInstance().setCity(addresses.get(0).getLocality());
//                        App.getInstance().setCountry(addresses.get(0).getCountryName());
//
////                        Toast.makeText(this, addresses.get(0).getLocality() + ", " + addresses.get(0).getCountryName(), Toast.LENGTH_LONG).show();
//                    }
//
//                } catch (IOException e) {
//
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                }
//            }
//        }
//    }

//    public static boolean isConnected() {
//
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        NetworkInfo netInfo = cm.getActiveNetworkInfo();
//
//        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
//
//            return true;
//        }
//
//        return false;
//    }

    public void updateGeoLocation() {

        // empty here ;)
    }



}