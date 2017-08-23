package com.gcme.wedechurch.adapters;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.ActivityCompat;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;

import com.gcme.wedechurch.model.PItemData;
import com.gcme.wedechurch.model.denominationchurchs;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.thebrownarrow.model.MyLocation;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by iblinfotech on 04/03/17.
 */

public class CustomMap {
    private GoogleMap googleMap;
    private Context mContext;
    private ArrayList<denominationchurchs> latLngsArrayList;
    private HashMap<String, String> markerImages = new HashMap<String, String>();
    private HashMap<Marker, PItemData> markerInfo = new HashMap<Marker, PItemData>();
    private HashMap<String, String> markerName = new HashMap<String, String>();
    private HashMap<String, String> markerAddress = new HashMap<String, String>();
    private HashMap<String, String> churchid = new HashMap<String, String>();
    Fragment mapFRAG;
    private Marker customMarker;
    public CustomMap(GoogleMap googleMap, ArrayList<denominationchurchs> latLng, Context context) {
        this.googleMap = googleMap;
        this.mContext = context;
        this.latLngsArrayList = latLng;

    }

    public void setCustomMapStyle(int mapstyle) {
        if (googleMap != null)
            try {
                boolean success = googleMap.setMapStyle(MapStyleOptions.loadRawResourceStyle(mContext, mapstyle));
                if (!success) {
                    Log.e("mLocation ", "detail activity,Style parsing failed.");
                }
            } catch (Resources.NotFoundException e) {
                Log.e("mLocation ", "detail activity, Can't find style. Error: " + e);
            }

    }

    public void addCustomPin() {
        if (googleMap != null) {
            googleMap.clear();

            for (int i = 0; i < latLngsArrayList.size(); i++) {
                addPin(latLngsArrayList.get(i), i);
            }
        }
    }

    public void addSelectedCustomPin(int position) {
        if (googleMap != null) {
            googleMap.clear();

            for (int i = 0; i < latLngsArrayList.size(); i++) {
                addMarkerSelectedPin(latLngsArrayList.get(i), i,position);
            }
        }
    }

    private static boolean isZooming = false;
    private static boolean isZoomingOut = false;

    public void addPin(denominationchurchs myLocation, int position) {
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        LatLng locationPoint =new LatLng(Double.parseDouble(myLocation.getChurchlatitude()), Double.parseDouble(myLocation.getChurchlongitude()));
        String chphone=myLocation.getChurchphone();
        String imageLocation=myLocation.getDenochurchimageUrl();
        String chid=myLocation.getchId();
        String chname=myLocation.getNameChurchs();
        String chdescription=myLocation.getLocation();

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locationPoint));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12), 2000, null);

        googleMap.setOnCameraChangeListener(new GoogleMap.OnCameraChangeListener() {
            @Override
            public void onCameraChange(CameraPosition cameraPosition) {

                if (cameraPosition.zoom >= 13.0f) {
                    if (!isZooming) {
                        googleMap.clear();

                        for (int i = 0; i < latLngsArrayList.size(); i++) {
                            addNearerPin(latLngsArrayList.get(i), i);
                        }
                        isZooming = true;
                        isZoomingOut = true;
                    }
                } else {
                    if (isZoomingOut) {
                        googleMap.clear();

                        for (int i = 0; i < latLngsArrayList.size(); i++) {
                            addNearerPin(latLngsArrayList.get(i), i);
                        }
                        isZoomingOut = false;
                        isZooming = false;
                    }
                }
            }
        });





        if (googleMap.getCameraPosition().zoom >= 13) {
            customMarker = googleMap.addMarker(new MarkerOptions()
                    .position(locationPoint)
                    .title(chname)
                    .snippet(chdescription.substring(0, Math.min(chdescription.length(), 80)) + "...")
                    .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_near_normal_pin))
                    .anchor(0.5f, 1));

        } else if (googleMap.getCameraPosition().zoom < 13) {

            customMarker = googleMap.addMarker(new MarkerOptions()
                    .position(locationPoint)
                    .title(chname)
                    .snippet(chdescription.substring(0, Math.min(chdescription.length(), 80)) + "...")
                    .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_normal_pin))
                    .anchor(0.5f, 1));



        }


//        if (googleMap.getCameraPosition().zoom >= 13) {
//            googleMap.addMarker(new MarkerOptions().position(locationPoint).icon(BitmapDescriptorFactory.fromBitmap(
//                    BitmapFactory.decodeResource(mContext.getResources(),
//                            com.thebrownarrow.customstyledmap.R.drawable.ic_near_normal_pin)))).setTag(position);
//        } else if (googleMap.getCameraPosition().zoom < 13) {
//            googleMap.addMarker(new MarkerOptions().position(locationPoint).icon(BitmapDescriptorFactory.fromBitmap(
//                    BitmapFactory.decodeResource(mContext.getResources(),
//                            com.thebrownarrow.customstyledmap.R.drawable.ic_normal_pin)))).setTag(position);
//        }



        if (imageLocation != null) {


            markerImages.put(customMarker.getId(), imageLocation);
        }

// markerImages.put(customMarker.getId(), Uri.parse(Config.APP_IMAGES_URL + itd.images.get(0).path));


        if (markerInfo != null) {
//                                markerInfo.put(customMarker, itd);
        }

        if (markerAddress != null) {
            markerAddress.put(customMarker.getId(), chphone);
        }

        if (markerName != null) {
            markerName.put(customMarker.getId(), chname);
        }

        if (churchid != null) {
            churchid.put(customMarker.getId(), chid);
        }

        googleMap.setInfoWindowAdapter(new MapPopupAdapter(mContext, ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)), markerImages, markerAddress,churchid,markerName));
        googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                googleMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                return true;
            }
        });





    }

    private void addNearerPin(denominationchurchs mLocation, int position) {
        LatLng locationPoint = new LatLng(Double.parseDouble(mLocation.getChurchlatitude()), Double.parseDouble(mLocation.getChurchlongitude()));
//        map.moveCamera(CameraUpdateFactory.newLatLng(locationPoint));


        if (googleMap.getCameraPosition().zoom >= 13) {
            customMarker = googleMap.addMarker(new MarkerOptions()
                    .position(locationPoint)
                    .title(mLocation.getNameChurchs())
                    .snippet(mLocation.getLocation().substring(0, Math.min(mLocation.getLocation().length(), 80)) + "...")
                    .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_near_normal_pin))
                    .anchor(0.5f, 1));

        } else if (googleMap.getCameraPosition().zoom < 13) {

            customMarker = googleMap.addMarker(new MarkerOptions()
                    .position(locationPoint)
                    .title(mLocation.getNameChurchs())
                    .snippet(mLocation.getLocation().substring(0, Math.min(mLocation.getLocation().length(), 80)) + "...")
                    .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_normal_pin))
                    .anchor(0.5f, 1));



        }

    }

    private void addMarkerSelectedPin(denominationchurchs mLocation, int position, int selectedPosition) {
        LatLng locationPoint = new LatLng(Double.parseDouble(mLocation.getChurchlatitude()), Double.parseDouble(mLocation.getChurchlongitude()));
//        map.moveCamera(CameraUpdateFactory.newLatLng(locationPoint));
        if (position == selectedPosition) {


            customMarker = googleMap.addMarker(new MarkerOptions()
                    .position(locationPoint)
                    .title(mLocation.getNameChurchs())
                    .snippet(mLocation.getLocation().substring(0, Math.min(mLocation.getLocation().length(), 80)) + "...")
                    .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_selected_pin))
                    .anchor(0.5f, 1));



        } else {
            if (googleMap.getCameraPosition().zoom >= 13) {


                customMarker = googleMap.addMarker(new MarkerOptions()
                        .position(locationPoint)
                        .title(mLocation.getNameChurchs())
                        .snippet(mLocation.getLocation().substring(0, Math.min(mLocation.getLocation().length(), 80)) + "...")
                        .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_near_normal_pin))
                        .anchor(0.5f, 1));




            } else if (googleMap.getCameraPosition().zoom < 13) {

                customMarker = googleMap.addMarker(new MarkerOptions()
                        .position(locationPoint)
                        .title(mLocation.getNameChurchs())
                        .snippet(mLocation.getLocation().substring(0, Math.min(mLocation.getLocation().length(), 80)) + "...")
                        .icon(BitmapDescriptorFactory.fromResource(com.thebrownarrow.customstyledmap.R.drawable.ic_normal_pin))
                        .anchor(0.5f, 1));




            }
        }
    }








}
