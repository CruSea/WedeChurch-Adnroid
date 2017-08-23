package com.gcme.wedechurch.Fragments;


import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.PermissionChecker;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.gcme.wedechurch.PermissionsFragment;
import com.gcme.wedechurch.R;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
//import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
//import com.gcme.addischurch.addischurch.Adapters.FavoriteAdapter;
//import com.gcme.addischurch.addischurch.Adapters.SearchAdapter;
//import com.gcme.addischurch.addischurch.Adapters.UIUtils;
//import com.gcme.addischurch.addischurch.DB.DatabaseAdaptor;
//import com.gcme.addischurch.addischurch.FileManager.FileManager;
//import com.gcme.addischurch.addischurch.JSON.RequestJson;
//import com.gcme.addischurch.addischurch.Model.PItemDatalocal;
//import com.gcme.addischurch.addischurch.Model.favorites;
//import com.gcme.addischurch.addischurch.Model.search;
//import com.gcme.addischurch.addischurch.Routing.GMapV2Direction;
//import com.gcme.addischurch.addischurch.Routing.GMapV2DirectionAsyncTask;
import com.gcme.wedechurch.Functions.Gps.GPSTracker;
import com.gcme.wedechurch.Routing.GMapV2Direction;
import com.gcme.wedechurch.Routing.GMapV2DirectionAsyncTask;
import com.gcme.wedechurch.activities.singleChurchDetail;
import com.gcme.wedechurch.adapters.CustomMap;
import com.gcme.wedechurch.adapters.MapPopupAdapter;
import com.gcme.wedechurch.adapters.denominationchurchsadaptor;
import com.gcme.wedechurch.app.App;
import com.gcme.wedechurch.model.Church;
import com.gcme.wedechurch.model.PItemData;
import com.gcme.wedechurch.model.denominationchurchs;
import com.gcme.wedechurch.util.GlobalUtils;
import com.gcme.wedechurch.util.Utils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;

import android.widget.ProgressBar;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.model.PolylineOptions;
import com.thebrownarrow.model.MyLocation;
//import com.gcme.addischurch.addischurch.Config;
//
//import com.gcme.addischurch.addischurch.Adapters.MapPopupAdapter;
//import com.gcme.addischurch.addischurch.Model.PItemData;
//import com.gcme.addischurch.addischurch.utilities.*;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.pnikosis.materialishprogress.ProgressWheel;
//import com.rey.material.widget.Slider;

import org.w3c.dom.Document;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;


import butterknife.ButterKnife;
import permission.auron.com.marshmallowpermissionhelper.FragmentManagePermission;
import permission.auron.com.marshmallowpermissionhelper.PermissionResult;
import permission.auron.com.marshmallowpermissionhelper.PermissionUtils;

import static android.content.Context.LOCATION_SERVICE;


public class MapFragment extends FragmentManagePermission{

    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - Private Variables
     * *------------------------------------------------------------------------------------------------
     */
    SupportMapFragment sMapFragment;
    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    FragmentManager manager;
    private String mLastQuery = "";
    private AppBarLayout mAppBar;
    boolean isGPSEnabled = false;
    boolean isNetworkEnabled = false;
    private ProgressBar loadingIndicator;
    Polyline polylin;

    MaterialDialog dialog;
    LatLng CurrentLocation;
    Marker mPositionMarker;
    Boolean isMarkerRotating = false;
    FloatingActionButton downbutton;
    LocationManager locationManager;
    public static Context context;

    private static final int MY_PERMISSION_ACCESS_FINE_LOCATION=11;
    private static final int MY_PERMISSION_ACCESS_COURSE_LOCATION=12;
    private boolean mIsDarkSearchTheme = false;


//    FileManager FM;
    String chid;
    String chcat_id;
    String chname;
    String chdescription;
    String chaddress;
    String chphone;
    String chemail;
    String chlat;
    String chlng;
    String chsearch_tag;
    String ImageLocationid;
    String imagename;

    Double latit;
    Double longi;
    LatLng pos;


//    DatabaseAdaptor DbHelper;
    public static GoogleMap mMap;
    private GoogleMap googleMap;
    private Marker customMarker;
    private LatLng markerLatLng;
//    private ProgressWheel progressWheel;
//    private ArrayList<PItemDatalocal> items;
    private TextView display_message;
    private RequestQueue queue;
    private boolean checkingLatLng = false;
    private HashMap<String, Uri> images = new HashMap<String, Uri>();

    private HashMap<String, String> markerImages = new HashMap<String, String>();
    private HashMap<Marker, PItemData> markerInfo = new HashMap<Marker, PItemData>();
    private HashMap<String, String> markerAddress = new HashMap<String, String>();
    private HashMap<String, String> markerName = new HashMap<String, String>();
    private HashMap<String, String> churchid = new HashMap<String, String>();

    private SharedPreferences pref;
    private int selectedCityId;
    private int selectedSubCatId;
    private double selectedRegionLat;
    private double selectedRegionLng;
    private double currentLongitude;
    private double currentLatitude;
    View marker;
    MapView mMapView;
    private String jsonStatusSuccessString;
    private SpannableString connectionErrorString;
    TextView addressTextView;
    FloatingActionButton routefab ;
    FloatingActionButton walk;
    FloatingActionButton car;
    ListView activeList;
    FloatingActionButton locationSearchFAB;
    LinearLayout bottomdrawerfab;

    private BottomSheetBehavior mBottomSheetBehavior;

    Adapter adapter;

    private static final String STATE_LIST = "State Adapter Data";

    SearchView searchView = null;

    RecyclerView mRecyclerView;
    TextView mMessage, mHeaderText;

    LinearLayout mHeaderContainer;


    public String queryText, currentQuery, oldQuery;

    public int itemCount;

    private int itemId = 0;
    private int arrayLength = 0;
    private Boolean loadingMore = false;
    private Boolean viewMore = false;
    private Boolean restore = false;

    int pastVisiblesItems = 0, visibleItemCount = 0, totalItemCount = 0;

    GPSTracker gps ;


    private Animation slide_out_down, slide_in_up;
    public static GoogleMap map;
//    private SupportMapFragment supportMapFragment;
    Marker hamburg, previousSelectedMarker;
    CustomMap customMap;
    private ArrayList<denominationchurchs> latLngsArrayList;
    private void setupViewPager(ViewPager viewPager) {
        adapter = new Adapter(getChildFragmentManager());
        adapter.addFragment(new churchBottomsheet(), "Church");
        adapter.addFragment(new eventsBottomSheet(), "Event");
        viewPager.setAdapter(adapter);
    }
    /**------------------------------------------------------------------------------------------------
     * End Block - Private Variables
     **------------------------------------------------------------------------------------------------*/


    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - Override Functions
     * *------------------------------------------------------------------------------------------------
     */
     View v;


    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

         v = inflater.inflate(R.layout.fragment_map, container, false);


        View bottomSheet = v.findViewById( R.id.bottom_sheet );
        mMapView = (MapView) v.findViewById(R.id.mapView1);
        bottomdrawerfab = (LinearLayout) v.findViewById(R.id.bootomsheet_menu);
        loadingIndicator = (ProgressBar) v.findViewById(R.id.maploadingindicator);
        final ViewPager viewPager = ButterKnife.findById(v, R.id.viewpager);
        TabLayout tabLayout = ButterKnife.findById(v, R.id.tabs);

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            setmap(v);
            loadMap(savedInstanceState, inflater, container);
//            initUI(v, inflater, container, savedInstanceState);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            askCompactPermissions(new String[]{PermissionUtils.Manifest_ACCESS_COARSE_LOCATION, PermissionUtils.Manifest_ACCESS_FINE_LOCATION}, new PermissionResult() {

                @Override
                public void permissionGranted() {
                    setmap(v);
                    loadMap(savedInstanceState, inflater, container);
//                    initUI(v, inflater, container, savedInstanceState);
                }
                @Override
                public void permissionDenied() {

                }
            });
        }



        loadingIndicator.setMax(100);
        gps = new GPSTracker(getActivity());

        if (savedInstanceState != null) {


            currentQuery = queryText = savedInstanceState.getString("queryText");



        } else {


            currentQuery = queryText = "";


        }
        // get the bottom sheet view
//        LinearLayout llBottomSheet = (LinearLayout) v.findViewById(R.id.bottom_sheet);

// init the bottom sheet behavior
        mBottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

//        LinearLayout llBottomSheet = (LinearLayout) v.findViewById(R.id.bottom_sheet);
//        bottomSheetBehavior = BottomSheetBehavior.from(llBottomSheet);


        mBottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
               if( mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_HIDDEN){
                   locationSearchFAB.setVisibility(View.VISIBLE);
                   bottomdrawerfab.setVisibility(View.VISIBLE);
                }else if(mBottomSheetBehavior.getState()==BottomSheetBehavior.STATE_COLLAPSED){
                   locationSearchFAB.setVisibility(View.INVISIBLE);
                   bottomdrawerfab.setVisibility(View.INVISIBLE);
               }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });


        context=getActivity();
        ButterKnife.bind(this, v);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.white));

        setHasOptionsMenu(true);
        return v;

    }

    private void setmap(View v) {

        routefab = (FloatingActionButton) v.findViewById(R.id.route1);
        walk = (FloatingActionButton) v.findViewById(R.id.walk1);
        car = (FloatingActionButton) v.findViewById(R.id.car1);
        locationSearchFAB = (FloatingActionButton) v.findViewById(R.id.mylocation1);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @Override
    public void onResume() {
        super.onResume();
        try{
            mMapView.onResume();
        }catch (Exception e){}

    }

    @Override
    public void onPause() {
        super.onPause();
        try{
            mMapView.onPause();
        }catch (Exception e){}

    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        try{
            mMapView.onLowMemory();
        }catch (Exception e){}

    }



    /**------------------------------------------------------------------------------------------------
     * End Block - Override Functions
     **------------------------------------------------------------------------------------------------*/

    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - INit data Functions
     * *------------------------------------------------------------------------------------------------
     */
    /*private void initData(View v) {

        DbHelper = new DatabaseAdaptor(getActivity());
        if(DbHelper.getAll().getCount()==0){



            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.sync1);
            fab.show();
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Turn On Internet connection and press the sync button !", Toast.LENGTH_LONG).show();


                    getActivity().startService(new Intent(getActivity(), RequestJson.class));

                }
            });

        }else{
            FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.sync1);
            fab.hide();
        }

    }
*/
    /**------------------------------------------------------------------------------------------------
     * End Block - Init Data Functions
     **------------------------------------------------------------------------------------------------*/

    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - Init UI Functions
     * *------------------------------------------------------------------------------------------------
     */
    private void initUI(View v, LayoutInflater inflater, ViewGroup container,
                        Bundle savedInstanceState) {
        if (Utils.isGooglePlayServicesOK(getActivity())) {
            Utils.psLog("Google Play Service is ready for Google Map");
            initFAB(v);


            loadMap(savedInstanceState, inflater, container);

            queue = Volley.newRequestQueue(getActivity().getApplicationContext());
        } else {
            showNoServicePopup();
        }
    }


        @Override
        public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

    //        MenuInflater menuInflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_search, menu);

            MenuItem searchItem = menu.findItem(R.id.search);

            SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

            if (searchItem != null) {

                searchView = (SearchView) MenuItemCompat.getActionView(searchItem);
            }

            if (searchView != null) {

                searchView.setQuery(queryText, false);

                searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
                searchView.setIconifiedByDefault(false);
                searchView.setIconified(false);

                SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
                searchAutoComplete.setHint(getText(R.string.placeholder_search));
                searchAutoComplete.setHintTextColor(getResources().getColor(R.color.white));

                searchView.clearFocus();

                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextChange(String newText) {

                        queryText = newText;

                        return false;
                    }

                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        queryText = query;
                        searchStart();

                        return false;
                    }
                });
            }

            super.onCreateOptionsMenu(menu, inflater);
        }


    public void searchStart() {

        currentQuery = getCurrentQuery();


    }


    public String getCurrentQuery() {

        String searchText = searchView.getQuery().toString();
        searchText = searchText.trim();

        return searchText;
    }


    private void initFAB(View v) {

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });



        locationSearchFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkingLatLng) {
                    if (readyLatLng()) {
                        loadingIndicator.setVisibility(View.VISIBLE);
                        getcurrentlocation(view);

                    } else {
                        showWaitPopup();
                    }
                } else {
                    loadingIndicator.setVisibility(View.VISIBLE);
                    getcurrentlocation(view);

                }
            }
        });

        bottomdrawerfab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                mBottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return false;
            }
        });

    }

    private void loadMap( Bundle savedInstanceState, LayoutInflater inflater, ViewGroup container) {






        if (Utils.isGooglePlayServicesOK(getActivity())) {
            Utils.psLog("Google Play Service is ready for Google Map");
            initFAB(v);



            queue = Volley.newRequestQueue(getActivity().getApplicationContext());





        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;
                map.setBuildingsEnabled(true);
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        LatLngBounds bounds = new LatLngBounds(
                                new LatLng(8.83900, 38.656596), // top left corner of map
                                new LatLng(9.0879298, 38.920954)); // bottom right corner

                        // Set the camera to the greatest possible zoom level that includes the
                        // bounds
                        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 13));


                        latLngsArrayList = new ArrayList<>();
                        latLngsArrayList.clear();
                        latLngsArrayList = getchurchList();

                        slide_out_down = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down);
                        slide_in_up = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_up);






//                        setContents();


//                                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        customMap = new CustomMap(map, latLngsArrayList, getActivity());

                        try {
                            customMap.setCustomMapStyle(R.raw.mapstyle);
                            // Customise the styling of the base map using a JSON object defined in a raw resource file.
                        } catch (Resources.NotFoundException e) {
                            Log.e("Explore detail activity", "Can't find style. Error: " + e);
                        }

                        handleMap();
                        customMap.addCustomPin();



                        if (getchurchList()!=null) {
//                            for (int i = 0; i < getchurchList().size(); i++) {
//
//
//                                chid = getchurchList().get(i).getchId();
//                                chcat_id = getchurchList().get(i).getchId();
//                                chname = getchurchList().get(i).getNameChurchs();
//                                chdescription = getchurchList().get(i).getLocation();
//                                chaddress = getchurchList().get(i).getLocation();
//                                chphone = getchurchList().get(i).getChurchphone();
//                                chemail = getchurchList().get(i).getChurchmail();
//                                chlat = getchurchList().get(i).getChurchlatitude();
//                                chlng = getchurchList().get(i).getChurchlongitude();
//                                imagename = getchurchList().get(i).getDenochurchimageUrl();
//
//
//
//
//                                if (!chlat.equals("") && !chlng.equals("")) {
//                                    latit = Double.parseDouble(chlat);
//                                    longi = Double.parseDouble(chlng);
//                                    pos = new LatLng(latit, longi);
//
//                                }
//                                pinpoint(chid, chcat_id, chname, chdescription, chaddress, chphone, chemail, pos,"", imagename);
//
//                            }







                        } else {


                            Toast.makeText(getActivity(), "There is no location data ", Toast.LENGTH_LONG).show();

                        }


                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                            @Override
                            public boolean onMarkerClick(final Marker marker) {
                                car.hide();
                                walk.hide();
                                routefab.show();
                                map.getUiSettings().setMyLocationButtonEnabled(false);
                                map.getUiSettings().setMapToolbarEnabled(false);
                                final double dmarkLat = marker.getPosition().latitude;
                                final double dmarkLong = marker.getPosition().longitude;
                                assert routefab != null;
                                //on click get Current location
                                routefab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        walk.show();
                                        car.show();
                                        map.getUiSettings().setMyLocationButtonEnabled(false);
                                        map.getUiSettings().setMapToolbarEnabled(false);
                                        LocationManager locationManager;
                                        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                                        // getting GPS status
                                        isGPSEnabled = locationManager
                                                .isProviderEnabled(LocationManager.GPS_PROVIDER);

                                        // getting network status
                                        isNetworkEnabled = locationManager
                                                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                                        /** check if GPS enabled**/

                                        routefab.hide();
                                        map.getUiSettings().setMyLocationButtonEnabled(false);
                                        map.getUiSettings().setMapToolbarEnabled(false);

                                        car.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                walk.hide();
//                                                progressWheel.setVisibility(View.VISIBLE);
                                                getRoutefromMarkerDriving(dmarkLat, dmarkLong);

                                            }
                                        });


                                        walk.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                car.hide();
//                                                progressWheel.setVisibility(View.VISIBLE);
                                                getRoutefromMarkerwalking(dmarkLat, dmarkLong);

                                            }
                                        });


                                    }

                                });
                                return false;
                            }

                        });









//                        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
//                            @Override
//                            public void onMapLoaded() {
//
//
//
//                            }
//
//
//                        });









                        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                            @Override
                            public void onInfoWindowClick(Marker marker) {

                                String churchid = marker.getId();

                                Intent i=new Intent(getActivity(),singleChurchDetail.class);
                                i.putExtra("selectedchurchid",churchid);

                                getActivity().startActivity(i);



                            }
                        });












//                        requestDataFromLocal();
//                        initFABroute();

                    }
                });
            }
        });

        } else {
            showNoServicePopup();
        }

        mMapView.onCreate(savedInstanceState);

        mMapView.onResume();

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        marker = inflater.inflate(R.layout.custom_marker, container, false);

    }

    /**------------------------------------------------------------------------------------------------
     * End Block - INit UI Functions
     **------------------------------------------------------------------------------------------------*/

    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - Private Functions
     * *------------------------------------------------------------------------------------------------
     */


    @TargetApi(23)
    private void checklocationpermission() {


        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( getContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(  new String[] {  android.Manifest.permission.ACCESS_FINE_LOCATION   },
                    MapFragment.MY_PERMISSION_ACCESS_FINE_LOCATION );
            requestPermissions(  new String[] {  android.Manifest.permission.ACCESS_COARSE_LOCATION  },
                    MapFragment.MY_PERMISSION_ACCESS_COURSE_LOCATION );

        }

    }





    private void requestDataFromLocal() {







        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

//                map = googleMap;
//
//                mMap.setBuildingsEnabled(true);


                latLngsArrayList = new ArrayList<>();
                latLngsArrayList.clear();
                latLngsArrayList = getchurchList();

                slide_out_down = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down);
                slide_in_up = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_up);



                map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {


//                        setContents();


                        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        customMap = new CustomMap(map, latLngsArrayList, getActivity());

                        try {
                            customMap.setCustomMapStyle(R.raw.mapstyle);
                            // Customise the styling of the base map using a JSON object defined in a raw resource file.
                        } catch (Resources.NotFoundException e) {
                            Log.e("Explore detail activity", "Can't find style. Error: " + e);
                        }

                        handleMap();
                        customMap.addCustomPin();



                        if (getchurchList()!=null) {
//                            for (int i = 0; i < getchurchList().size(); i++) {
//
//
//                                chid = getchurchList().get(i).getchId();
//                                chcat_id = getchurchList().get(i).getchId();
//                                chname = getchurchList().get(i).getNameChurchs();
//                                chdescription = getchurchList().get(i).getLocation();
//                                chaddress = getchurchList().get(i).getLocation();
//                                chphone = getchurchList().get(i).getChurchphone();
//                                chemail = getchurchList().get(i).getChurchmail();
//                                chlat = getchurchList().get(i).getChurchlatitude();
//                                chlng = getchurchList().get(i).getChurchlongitude();
//                                imagename = getchurchList().get(i).getDenochurchimageUrl();
//
//
//
//
//                                if (!chlat.equals("") && !chlng.equals("")) {
//                                    latit = Double.parseDouble(chlat);
//                                    longi = Double.parseDouble(chlng);
//                                    pos = new LatLng(latit, longi);
//
//                                }
//                                pinpoint(chid, chcat_id, chname, chdescription, chaddress, chphone, chemail, pos,"", imagename);
//
//                            }







                        } else {


                            Toast.makeText(getActivity(), "There is no location data ", Toast.LENGTH_LONG).show();

                        }


                        map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {

                            @Override
                            public boolean onMarkerClick(final Marker marker) {
                                car.hide();
                                walk.hide();
                                routefab.show();
                                map.getUiSettings().setMyLocationButtonEnabled(false);
                                map.getUiSettings().setMapToolbarEnabled(false);
                                final double dmarkLat = marker.getPosition().latitude;
                                final double dmarkLong = marker.getPosition().longitude;
                                assert routefab != null;
                                //on click get Current location
                                routefab.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        walk.show();
                                        car.show();
                                        map.getUiSettings().setMyLocationButtonEnabled(false);
                                        map.getUiSettings().setMapToolbarEnabled(false);
                                        LocationManager locationManager;
                                        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
                                        // getting GPS status
                                        isGPSEnabled = locationManager
                                                .isProviderEnabled(LocationManager.GPS_PROVIDER);

                                        // getting network status
                                        isNetworkEnabled = locationManager
                                                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

                                        /** check if GPS enabled**/

                                        routefab.hide();
                                        map.getUiSettings().setMyLocationButtonEnabled(false);
                                        map.getUiSettings().setMapToolbarEnabled(false);

                                        car.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                walk.hide();
//                                                progressWheel.setVisibility(View.VISIBLE);
                                                getRoutefromMarkerDriving(dmarkLat, dmarkLong);

                                            }
                                        });


                                        walk.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                car.hide();
//                                                progressWheel.setVisibility(View.VISIBLE);
                                                getRoutefromMarkerwalking(dmarkLat, dmarkLong);

                                            }
                                        });


                                    }

                                });
                                return false;
                            }

                        });








                    }


                });









                map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
                    @Override
                    public void onInfoWindowClick(Marker marker) {

                        String churchid = marker.getId();

                        Intent i=new Intent(getActivity(),singleChurchDetail.class);
                        i.putExtra("selectedchurchid",churchid);

                        getActivity().startActivity(i);



                    }
                });






            }});
    }

    private void initFABroute() {

        map.getUiSettings().setMyLocationButtonEnabled(false);
        map.getUiSettings().setMapToolbarEnabled(false);
        LocationManager locationManager;
        locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        /** check if GPS enabled**/


        routefab.hide();
        routefab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isGPSEnabled) {
                    if (isNetworkEnabled) {
                        walk.show();
                        car.show();
                        car.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                walk.hide();
//                                progressWheel.setVisibility(View.VISIBLE);
                                getRouteCurrentLocation();

                            }
                        });


                        walk.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                car.hide();
//                                progressWheel.setVisibility(View.VISIBLE);
                                getRoutewalking();
                            }
                        });


                    }else {
                        Toast.makeText(getActivity(), "No Network", Toast.LENGTH_LONG);
                    }

                } else {
                   gps.showSettingsAlert();

                }


            }




        });


    }


    /** THIS IS TO ROUTE TO THE MARKER LOCATION **/

    protected void route(final LatLng sourcePosition, LatLng destPosition, String mode) {
        final android.os.Handler handler = new android.os.Handler() {


            public void handleMessage(Message msg) {
                try {

                    if (polylin != null) {
                        map.clear();
                        checklocationpermission();
                        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            return;
                        }
                        map.setMyLocationEnabled(true);
                        requestDataFromLocal();
                    }
                    PolylineOptions rectLine;


                    Document doc = (Document) msg.obj;
                    GMapV2Direction md = new GMapV2Direction();
                    ArrayList<LatLng> directionPoint = md.getDirection(doc);
                    rectLine = new PolylineOptions().width(15).color(getResources().getColor(R.color.colorCounterBackground));
                    // plotemylocation();
                    for (int i = 0; i < directionPoint.size(); i++) {
                        rectLine.add(directionPoint.get(i));
                    }
                    polylin = map.addPolyline(rectLine);

                    String time=md.getDurationText(doc);
                    //Toast.makeText(getApplicationContext(),"you will get there in "+time+" minutes", Toast.LENGTH_LONG).show();
                    float zoom = 17;
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(sourcePosition, zoom));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        };

        new GMapV2DirectionAsyncTask(handler, sourcePosition, destPosition, mode).execute();
    }



    public void getRouteCurrentLocation() {


        checkingLatLng = false;

        getCurrentLocation(addressTextView);
        if( checkingLatLng==false) {


            LatLng Currentlocation = new LatLng(currentLatitude, currentLongitude);
            // Location location = mMap.getMyLocation();
            if (Currentlocation != null) {
                // if (location != null) {
//                                                double latitude = location.getLatitude();
//                                                double longitude = location.getLongitude();




                Intent intent = getActivity().getIntent();
                String dLon = intent.getStringExtra("Lon");
                final String dLat = intent.getStringExtra("Lat");

                double dlatitude = Double.parseDouble(dLat);
                double dlongitude = Double.parseDouble(dLon);
                final LatLng destLocation = new LatLng(dlatitude, dlongitude);

                String modedriving = "driving";
                route(Currentlocation, destLocation, modedriving);


                ploteCurrentLocation(currentLatitude, currentLongitude);
            }


            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.getUiSettings().setMapToolbarEnabled(false);
            checklocationpermission();






        }

    }



    public void getRoutewalking() {


        checkingLatLng = false;

        getCurrentLocation(addressTextView);
        if( checkingLatLng==false) {


            LatLng Currentlocation = new LatLng(currentLatitude, currentLongitude);
            // Location location = mMap.getMyLocation();
            if (Currentlocation != null) {
                // if (location != null) {
//                                                double latitude = location.getLatitude();
//                                                double longitude = location.getLongitude();


                Intent intent = getActivity().getIntent();
                String dLon = intent.getStringExtra("Lon");
                final String dLat = intent.getStringExtra("Lat");

                double dlatitude = Double.parseDouble(dLat);
                double dlongitude = Double.parseDouble(dLon);
                final LatLng destLocation = new LatLng(dlatitude, dlongitude);


                String modewalking = "walking";
                route(Currentlocation, destLocation, modewalking);


                ploteCurrentLocation(currentLatitude, currentLongitude);
            }


            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.getUiSettings().setMapToolbarEnabled(false);
            checklocationpermission();


        }

    }




    public void getRoutefromMarkerwalking(double dlatitude,double dlongitude) {


        checkingLatLng = false;

        getCurrentLocation(addressTextView);
        if( checkingLatLng==false) {


            car.hide();
            checklocationpermission();

            LatLng Currentlocation = new LatLng(currentLatitude, currentLongitude);
            // Location location = mMap.getMyLocation();
            if (Currentlocation != null) {

                final LatLng destLocation = new LatLng(dlatitude, dlongitude);
                String modewalking = "walking";
                route(Currentlocation, destLocation, modewalking);
                ploteCurrentLocation(currentLatitude, currentLongitude);
            }


            map.getUiSettings().setMyLocationButtonEnabled(false);
            map.getUiSettings().setMapToolbarEnabled(false);
        }


    }



    public void getRoutefromMarkerDriving(double dmarkLat, double dmarkLong) {


        checkingLatLng = false;

        getCurrentLocation(addressTextView);
        if( checkingLatLng==false) {

            walk.hide();
            checklocationpermission();

            LatLng Currentlocation = new LatLng(currentLatitude, currentLongitude);
            if (Currentlocation != null) {
                final LatLng destLocation = new LatLng(dmarkLat, dmarkLong);
                String modedriving = "driving";
                route(Currentlocation, destLocation, modedriving);
            }

        }

    }






    /**------------------------------------------------------------------------------------------------
     * End Block - Private Functions
     **------------------------------------------------------------------------------------------------*/

    /**
     * ------------------------------------------------------------------------------------------------
     * Start Block - Public Functions
     * *------------------------------------------------------------------------------------------------
     */

    public void getcurrentlocation(View view) {
        checkingLatLng = false;

        getCurrentLocation(addressTextView);
        if( checkingLatLng==false) {
            ploteCurrentLocation(currentLatitude, currentLongitude);
        }

    }

    private void ploteCurrentLocation(double currentLatitude, double currentLongitude) {

        checklocationpermission();
        locationManager = (LocationManager) getContext().getSystemService(LOCATION_SERVICE);

        // getting GPS status
        isGPSEnabled = locationManager
                .isProviderEnabled(LocationManager.GPS_PROVIDER);

        // getting network status
        isNetworkEnabled = locationManager
                .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        /**//**//**//** check if GPS enabled**//**//**//**/


            if (isNetworkEnabled) {
                Toast.makeText(getActivity(), "please wait..." , Toast.LENGTH_SHORT).show();

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    return;
                }

                mMap.setMyLocationEnabled(true);
                mMap.getUiSettings().setMyLocationButtonEnabled(false);

                LatLng target = new LatLng(currentLatitude, currentLongitude);

                if (target!= null) {

                    FloatingActionButton fab2 = (FloatingActionButton) getView().findViewById(R.id.route1);
                    fab2.hide();


                    final CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(target)      // Sets the center of the map to Mountain View
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(30)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   //


                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    mMap.setOnMyLocationButtonClickListener(new GoogleMap.OnMyLocationButtonClickListener() {
                        @Override
                        public boolean onMyLocationButtonClick() {
                            loadingIndicator.setVisibility(View.INVISIBLE);
                            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            return true;
                        }
                    });

                }
            }





    }


    public void showWaitPopup() {

        GPSTracker gps = new GPSTracker(getActivity());


        if (gps.canGetLocation()) {

            new MaterialDialog.Builder(getActivity())
                    .title(R.string.pls_wait)
                    .content(R.string.gps_not_ready)
                    .positiveText(R.string.OK)
                    .show();
        } else {
            gps.showSettingsAlert();


        }

    }

    public void showNoServicePopup() {
        new MaterialDialog.Builder(getActivity())
                .title(R.string.sorry_title)
                .content(R.string.no_google_play)
                .positiveText(R.string.OK)
                .show();
    }

    public boolean readyLatLng() {
        GPSTracker gps = new GPSTracker(getActivity());

        if (gps.canGetLocation()) {
            currentLatitude = gps.getLatitude();
            currentLongitude = gps.getLongitude();
            if (currentLatitude != 0.0 && currentLongitude != 0.0) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }

    }

    public void getCurrentLocation(TextView tv) {


        if (gps.canGetLocation()) {
            currentLatitude = gps.getLatitude();
            currentLongitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
            checkingLatLng = true;


        }


    }


    private void pinpoint(final String chid, final String chcat_id, final String chname, final String chdescription, String chaddress, final String chphone, String chemail, final LatLng pos, String chsearch_tag, final String imageLocation) {
        customMarker = mMap.addMarker(new MarkerOptions()
                .position(pos)
                .title(chname)
                .snippet(chdescription.substring(0, Math.min(chdescription.length(), 80)) + "...")
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.church_icon))
                .anchor(0.5f, 1));


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

        mMap.setInfoWindowAdapter(new MapPopupAdapter(getActivity(), getActivity().getLayoutInflater(), markerImages, markerAddress,churchid,markerName));
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                marker.showInfoWindow();
                mMap.animateCamera(CameraUpdateFactory.newLatLng(marker.getPosition()));
                return true;
            }
        });


    }






    static class Adapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();
        public Adapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }
        public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mFragmentTitles.add(title);
        }

        @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
        }
        @Override
        public int getCount() {
        return mFragments.size();
        }
        @Override
        public CharSequence getPageTitle(int position) {
        return mFragmentTitles.get(position);
    }

}


    private void setContents() {
        latLngsArrayList = new ArrayList<>();
        latLngsArrayList.clear();
//        latLngsArrayList = GlobalUtils.getLatLongArray();

        slide_out_down = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_out_down);
        slide_in_up = AnimationUtils.loadAnimation(getActivity(), R.anim.slide_in_up);



//        supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);


        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                map = googleMap;
                map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                customMap = new CustomMap(map, latLngsArrayList, getActivity());

                try {
                    customMap.setCustomMapStyle(R.raw.mapstyle);
                    // Customise the styling of the base map using a JSON object defined in a raw resource file.
                } catch (Resources.NotFoundException e) {
                    Log.e("Explore detail activity", "Can't find style. Error: " + e);
                }

                handleMap();
                customMap.addCustomPin();

            }
        });
    }

    /**------------------------------------------------------------------------------------------------
     * End Block - Public Functions
     **------------------------------------------------------------------------------------------------*/

    private static ArrayList<denominationchurchs> getchurchList() {
        ArrayList<denominationchurchs> list = new ArrayList<>();

        list.add(new denominationchurchs("1","Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("2","Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.785429","8.999935"));
        list.add(new denominationchurchs("3","Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","9.026339","38.817945"));
        list.add(new denominationchurchs("4","Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","9.0094697","38.8033294"));
        list.add(new denominationchurchs("5","Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.8033294","9.0094697"));

        return list;
    }


    private void handleMap() {
        if (map != null) {
            if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }

            map.setMyLocationEnabled(true);

            map.getUiSettings().setMapToolbarEnabled(false);
            map.getUiSettings().setZoomControlsEnabled(false);

            map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {

                    final int mPosition = (int) marker.getTag();
                    try {
                        if (previousSelectedMarker != null) {

                            denominationchurchs location = latLngsArrayList.get(mPosition);

                            if (map.getCameraPosition().zoom >= 13) {
                                previousSelectedMarker.setIcon(BitmapDescriptorFactory.fromBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                                R.drawable.ic_near_normal_pin)));
                            } else if (map.getCameraPosition().zoom < 13) {
                                previousSelectedMarker.setIcon(BitmapDescriptorFactory.fromBitmap(
                                        BitmapFactory.decodeResource(getResources(),
                                                R.drawable.ic_normal_pin)));
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    marker.setIcon(null);
                    marker.setIcon(BitmapDescriptorFactory.fromBitmap(
                            BitmapFactory.decodeResource(getResources(),
                                    R.drawable.ic_selected_pin)));

                    previousSelectedMarker = marker;

//                    if (event_pager.getVisibility() != View.VISIBLE) {
//
//                        event_pager.startAnimation(slide_in_up);
//                        slide_in_up.setAnimationListener(new Animation.AnimationListener() {
//                            @Override
//                            public void onAnimationStart(Animation arg0) {
//
//                                event_pager.setVisibility(View.VISIBLE);
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animation arg0) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animation arg0) {
//
//                            }
//                        });
//
//                        event_pager.setCurrentItem(mPosition, true);
//
//                    } else {
//                        event_pager.setCurrentItem(mPosition, true);
//                    }

                    return false;
                }
            });

//            map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//                @Override
//                public void onMapClick(LatLng latLng) {

//                    if (event_pager.getVisibility() == View.VISIBLE) {
//                        event_pager.startAnimation(slide_out_down);
//
//                        slide_out_down.setAnimationListener(new Animation.AnimationListener() {
//                            @Override
//                            public void onAnimationStart(Animation arg0) {
//
//                            }
//
//                            @Override
//                            public void onAnimationRepeat(Animation arg0) {
//
//                            }
//
//                            @Override
//                            public void onAnimationEnd(Animation arg0) {
//                                event_pager.setVisibility(View.GONE);
//                                event_pager.clearAnimation();
//                            }
//                        });
//                    }
//                }
//            });


        } else {
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {

                    map = googleMap;
                    map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                    customMap = new CustomMap(map, latLngsArrayList,getActivity());

                    try {
                        customMap.setCustomMapStyle(R.raw.mapstyle);
                        // Customise the styling of the base map using a JSON object defined in a raw resource file.
                    } catch (Resources.NotFoundException e) {
                        Log.e("Explore detail activity", "Can't find style. Error: " + e);
                    }

                    handleMap();

                }
            });

        }
    }




}
