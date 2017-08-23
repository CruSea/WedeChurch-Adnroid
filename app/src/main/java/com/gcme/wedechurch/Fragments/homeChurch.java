package com.gcme.wedechurch.Fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.model.denominationchurchs;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;

import static com.gcme.wedechurch.Fragments.MapFragment.context;

public class homeChurch extends Fragment {
    public static GoogleMap mMap;
    SupportMapFragment sMapFragment;
    public static final String ORIENTATION = "orientation";
    FragmentManager manager;

    private RecyclerView mRecyclerView;
    ImageButton favred, favwhite, churchred, churchwhite;
    private boolean mHorizontal;
    ProgressDialog pd;
    ArrayList<String> aa = new ArrayList<String>();
    ArrayList<String> num= new ArrayList<String>();
    TextView contactsview,webview,sermonview;
    String SelectedSearchitem,Selectedmarkname;

    ImageView BannerImage;
    Toolbar Detailtoolbar;
    RequestQueue queue;
    String UrlId;
    String url = "http://localchurchnew-001-site1.ctempurl.com/api/churchEvents";
    RecyclerView recyclerView;
    ListView scheduleList;
    FloatingActionButton Detailgetdirection;
    ImageView ToolbarChurchImage;
    TextView homechurchname;
    Toolbar toolbar;

//    List<EventHandler> feedsList = new ArrayList<EventHandler>();
//    RecyclerEventAdapter adapter;
    public homeChurch() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_home_church, container, false);
        ToolbarChurchImage= (ImageView) view.findViewById(R.id.homechurchheaderimage);
        homechurchname= (TextView) view.findViewById(R.id.homechurchname);
        Intent intent = getActivity().getIntent();
        Bundle bd = intent.getExtras();
//        toolbar = (Toolbar) view.findViewById(R.id.homechurchtoolbar);
        if(bd != null)
        {
            String churchid  = bd.getString("selectedchurchid");

            populatedatausingid(churchid);
        }


//        if(getArguments().getString("Key")!=null) {
//            String SelectedSearchitem = getArguments().getString("Key");
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.inflateMenu(R.menu.main);
//            toolbar.setTitle(SelectedSearchitem);
//        }

//        Detailgetdirection= (FloatingActionButton) findViewById(R.id.detailgetdirection);
//        contactsview = (TextView) findViewById(R.id.phoneno);
//        webview = (TextView) findViewById(R.id.web);
//        Detailtoolbar= (Toolbar) findViewById(R.id.detailtoolbar);
//        BannerImage= (ImageView) findViewById(R.id.headerimage);
//        scheduleList= (ListView) findViewById(R.id.ScheduleList);
//        churchwhite = (ImageButton) findViewById(R.id.homechurch1);
//        churchred = (ImageButton) findViewById(R.id.favhomechurch2);
//        favred = (ImageButton) findViewById(R.id.favred);
//        favwhite = (ImageButton) findViewById(R.id.favwhite);
//        favwhite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //   Toast.makeText(getActivity(), "white clicked", Toast.LENGTH_SHORT).show();
//                if(getArguments().getString("Keyid")!=null) {
//                    String Selecteditemid = getArguments().getString("Keyid");
//                    if(DbHelper.checkfavDataRowById(Selecteditemid).getCount()==0){
//                        DbHelper.Insertfav(Selecteditemid);
//
//                    }
//
//
//                }
//
//                favwhite.setVisibility(View.GONE);
//                favred.setVisibility(View.VISIBLE);
//            }
//        });
//        favred.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getArguments().getString("Keyid")!=null) {
//                    String Selecteditemid = getArguments().getString("Keyid");
//                    DbHelper.deletefavData(Selecteditemid);
//                }
//
//                favwhite.setVisibility(View.VISIBLE);
//                favred.setVisibility(View.GONE);
//            }
//        });
//        churchwhite.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//
//                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
//                alertDialog.setCancelable(false);
//                // Setting Dialog Title
//                alertDialog.setTitle("Wede Church");
//
//                // Setting Dialog Message
//                alertDialog.setMessage("Do you want make "+ getArguments().getString("MarkerName")+ " your home church? ");
//
//                // On pressing Settings button
//                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int which) {
//
//
//                        if(getArguments().getString("Keyid")!=null) {
//                            SelectedSearchitem = getArguments().getString("Keyid");
//                            DbHelper.changehome();
//                            DbHelper.InsertHome(SelectedSearchitem);
//                        }
//
//
//
//
//                        churchwhite.setVisibility(View.GONE);
//                        churchred.setVisibility(View.VISIBLE);
//
//                    }
//                });
//
//                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//
//                // Showing Alert Message
//                alertDialog.show();
//
//
//
//
//            }
//        });
//        churchred.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
////
////                final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
////                alertDialog.setCancelable(false);
////                // Setting Dialog Title
////                alertDialog.setTitle("Wede Church");
////
////
////                // Setting Dialog Message
////                alertDialog.setMessage("Do remove "+ getArguments().getString("MarkerName")+ " from your home church? ");
////
////                // On pressing Settings button
////                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog,int which) {
////
////
////                        DbHelper.changehome();
////
////
////
////
////                        //   Toast.makeText(getActivity(), "white clicked", Toast.LENGTH_SHORT).show();
////
////                        churchred.setVisibility(View.GONE);
////                        churchwhite.setVisibility(View.VISIBLE);
////
////                    }
////                });
////
////                alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.dismiss();
////                    }
////                });
////
////
////                // Showing Alert Message
////                alertDialog.show();
////
////
//
//
//            }
//        });
//
//
//
////        if(getArguments().getString("Keyid")!=null) {
////            SelectedSearchitem = getArguments().getString("Key");
////            String Selecteditemid = getArguments().getString("Keyid");
////            Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
////            toolbar.inflateMenu(R.menu.main);
////
////            Detailtoolbar.setTitle(SelectedSearchitem);
////            //UrlId=url+Selecteditemid;
////
////
////            FillContents(Selecteditemid);
////            getschedule(Selecteditemid);
////
////            StatusChecker(Selecteditemid);
////
////
////        }
//
//        if(getArguments().getString("MarkerName")!=null) {
//            Selectedmarkname = getArguments().getString("MarkerName");
//
//
//            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//            toolbar.inflateMenu(R.menu.main);
//
//            Detailtoolbar.setTitle(Selectedmarkname);
//
//
//
//            fillbyname(Selectedmarkname);
//
//
//
//
//
////             DbHelper = new DatabaseAdaptor(getActivity());
////
////             Cursor cursor = DbHelper.getMarkerDataRowByname(Selectedmarkname);
////             if (cursor != null) {
////                 final String Longitude = cursor.getString(cursor.getColumnIndex(DbHelper.LONGITUDE));
////                 final String Latitude = cursor.getString(cursor.getColumnIndex(DbHelper.LATITUDE));
//
//
//            FloatingActionButton getdirection = (FloatingActionButton) view.findViewById(R.id.detailgetdirection);
//            getdirection.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    Home_fragment secondFrag = new Home_fragment();
//                    Bundle args = new Bundle();
////                         args.putString("Longitude",Longitude);
////                         args.putString("Latitude",Latitude);
//
//                    secondFrag.setArguments(args);
//                    getFragmentManager()
//                            .beginTransaction()
//                            .replace(R.id.fragment_container, secondFrag)
//                            .addToBackStack("tag_back_Home_fragment")
//                            .commit();
//
//
//                }
//            });
//
//
//        }
//
//
//
//
//
//
//
//
//
//
//

        return view;


    }

    private void populatedatausingid(String churchid) {

        ArrayList<denominationchurchs> churcdata=getallchurchList();



        for( denominationchurchs a : churcdata) {
// or equalsIgnoreCase or whatever your conditon is
            if (churchid == a.getchId()) {

               String churchimageurl= a.getDenochurchimageUrl();
                String churchname=a.getNameChurchs();

                populatedata(churchimageurl,churchname);
            }
    }
    }

    private void populatedata(String churchimageurl,String churchname) {


        homechurchname.setText(churchname);
        Glide.with(context)
                .load(churchimageurl)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(ToolbarChurchImage);



    }

    private static ArrayList<denominationchurchs> getallchurchList() {
        ArrayList<denominationchurchs> list = new ArrayList<>();

        list.add(new denominationchurchs("1","Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("2","Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("3","Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("4","Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("5","Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));

        return list;
    }
}
