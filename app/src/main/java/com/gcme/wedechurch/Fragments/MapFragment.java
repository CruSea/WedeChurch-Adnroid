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
import android.database.Cursor;
import android.graphics.Bitmap;
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
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.SpannableString;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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
import com.gcme.wedechurch.app.App;
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

import static android.content.Context.LOCATION_SERVICE;


public class MapFragment extends Fragment implements PermissionsFragment{

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
//    private HashMap<Marker, PItemData> markerInfo = new HashMap<Marker, PItemData>();
    private HashMap<String, String> markerAddress = new HashMap<String, String>();
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_map, container, false);


        View bottomSheet = v.findViewById( R.id.bottom_sheet );
        mMapView = (MapView) v.findViewById(R.id.mapView1);
        loadingIndicator = (ProgressBar) v.findViewById(R.id.maploadingindicator);
        loadingIndicator.setMax(100);

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

        routefab = (FloatingActionButton) v.findViewById(R.id.route1);
        walk = (FloatingActionButton) v.findViewById(R.id.walk1);
        car = (FloatingActionButton) v.findViewById(R.id.car1);
        locationSearchFAB = (FloatingActionButton) v.findViewById(R.id.mylocation1);
        bottomdrawerfab = (LinearLayout) v.findViewById(R.id.bootomsheet_menu);
        context=getActivity();
        ButterKnife.bind(this, v);
        final ViewPager viewPager = ButterKnife.findById(v, R.id.viewpager);
        setupViewPager(viewPager);
        TabLayout tabLayout = ButterKnife.findById(v, R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabTextColors(getResources().getColorStateList(R.color.white));
        initUI(v, inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        return v;

    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
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

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                mMap = googleMap;
                mMap.setBuildingsEnabled(true);
                mMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
                    @Override
                    public void onMapLoaded() {
                        LatLngBounds bounds = new LatLngBounds(
                                new LatLng(8.83900, 38.656596), // top left corner of map
                                new LatLng(9.0879298, 38.920954)); // bottom right corner

                        // Set the camera to the greatest possible zoom level that includes the
                        // bounds
                        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 13));


                    }
                });
            }
        });

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


    @Override
    public String[] requiredPermissions() {
        return new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

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
        GPSTracker gps = new GPSTracker(getActivity());


        if (gps.canGetLocation()) {
            currentLatitude = gps.getLatitude();
            currentLongitude = gps.getLongitude();

        } else {
            gps.showSettingsAlert();
            checkingLatLng = true;


        }


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



    /**------------------------------------------------------------------------------------------------
     * End Block - Public Functions
     **------------------------------------------------------------------------------------------------*/


}
