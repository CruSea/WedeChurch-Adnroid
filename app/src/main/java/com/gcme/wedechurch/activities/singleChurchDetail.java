package com.gcme.wedechurch.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import java.util.List;

import static com.gcme.wedechurch.Fragments.MapFragment.context;

public class singleChurchDetail extends AppCompatActivity {
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
    Toolbar toolbar;

//    List<EventHandler> feedsList = new ArrayList<EventHandler>();
//    RecyclerEventAdapter adapter;
    public singleChurchDetail() {
        // Required empty public constructor
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_churchdetail);
        ToolbarChurchImage= (ImageView) findViewById(R.id.churchheaderimage);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        toolbar = (Toolbar) findViewById(R.id.churchtoolbar);
        if(bd != null)
        {
            long churchid  = bd.getLong("selectedchurchid");

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
//        churchwhite = (ImageButton) findViewById(R.id.favhomechurch1);
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



    }

    private void populatedatausingid(long churchid) {

        ArrayList<denominationchurchs> churcdata=getallchurchList();



        for( denominationchurchs a : churcdata) {
// or equalsIgnoreCase or whatever your conditon is
            if (a.getchId()==churchid) {

               String churchimageurl= a.getDenochurchimageUrl();
                String churchname=a.getNameChurchs();

                populatedata(churchimageurl,churchname);
            }
    }
    }

    private void populatedata(String churchimageurl,String churchname) {


        toolbar.setTitle(churchname);
        Glide.with(context)
                .load(churchimageurl)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(ToolbarChurchImage);



    }

    public static ArrayList<denominationchurchs> getallchurchList() {
        ArrayList<denominationchurchs> list = new ArrayList<>();

        list.add(new denominationchurchs(1,"Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg"));
        list.add(new denominationchurchs(2,"Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg"));
        list.add(new denominationchurchs(3,"Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg"));
        list.add(new denominationchurchs(4,"Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg"));
        list.add(new denominationchurchs(5,"Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg"));

        return list;
    }
}
