package com.gcme.wedechurch.activities;

import android.app.ProgressDialog;
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
import com.gcme.wedechurch.MainActivity;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.singleChurchEventAdapter;
import com.gcme.wedechurch.adapters.ScheduleAdapter;
import com.gcme.wedechurch.model.Church;
import com.gcme.wedechurch.model.Fav;
import com.gcme.wedechurch.model.Schedules;
import com.gcme.wedechurch.model.denominationchurchs;
import com.gcme.wedechurch.model.eventchurchs;
import com.gcme.wedechurch.util.Utils;
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

    String url = "http://localchurchnew-001-site1.ctempurl.com/api/churchEvents";

   FloatingActionButton Detailgetdirection;
    ImageView ToolbarChurchImage;
    ImageButton addfav,removefav;
    TextView ChurchPhone,ChurchLink,ChurchEmail;
    String ChurchLongitude,ChurchLattitude;
    Toolbar toolbar;
    ArrayList<denominationchurchs> churcdata;
    ArrayList<Schedules> churcscheduledata;
    String churchid;
    RecyclerView recyclerView;
    String churchimageurl;
    String churchname;
    String churchphone;
    String churchwebsite;
    String churchmail;
    String churchlocation;
    String churchlongitude ;
    String churchlatitude;
    ListView scheduleList;
    protected long id;


    private singleChurchEventAdapter mGoogleCardsnewsAdapter;

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
        addfav= (ImageButton) findViewById(R.id.addfav);
        removefav= (ImageButton) findViewById(R.id.removefav);
        ChurchPhone= (TextView) findViewById(R.id.churchphone);
        ChurchLink= (TextView) findViewById(R.id.churchLink);
        ChurchEmail= (TextView) findViewById(R.id.churchmail);
        Intent intent = getIntent();
        Bundle bd = intent.getExtras();
        toolbar = (Toolbar) findViewById(R.id.churchtoolbar);
        churcdata=getallchurchList();
        churcscheduledata=getchurchScheduleList();
        scheduleList= (ListView) findViewById(R.id.ScheduleList);
        Detailgetdirection= (FloatingActionButton) findViewById(R.id.getchurchdirection);
        recyclerView = (RecyclerView) findViewById(R.id.singlechurchevent);
        if(bd != null)
        {
            churchid  = bd.getString("selectedchurchid");
            populatedatausingid(churchid);
            getschedule();
        }


        Detailgetdirection.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    for( denominationchurchs a : churcdata) {
                        if (a.getchId()==churchid) {
                            String churchlongitude = a.getChurchlongitude();
                            String churchlatitude=a.getChurchlatitude();


                            Intent i=new Intent(singleChurchDetail.this, MainActivity.class);
                            i.putExtra("Longitude", churchlongitude);
                            i.putExtra("Latitude", churchlatitude);
                            startActivity(i);



                        }
                    }

                }
            });

        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        mGoogleCardsnewsAdapter = new singleChurchEventAdapter(this,getchurchList());

        recyclerView.setAdapter(mGoogleCardsnewsAdapter);
        recyclerView.setLayoutManager(layoutManager);

//
//        if (fav != null) {
//            addfav.setVisibility(View.VISIBLE);
//            removefav.setVisibility(View.INVISIBLE);
//        }



        List<Fav> fav=new ArrayList<>();
        long count = Fav.count(Fav.class);
        if(count>-1)
        {
            List<Fav> allfavs = Fav.listAll(Fav.class);
           if (allfavs.size()>0){

               if (Fav.find(Fav.class, "church_Id = ?", churchid) == null) {

                   addfav.setVisibility(View.VISIBLE);
                   removefav.setVisibility(View.INVISIBLE);


               } else {
                   addfav.setVisibility(View.INVISIBLE);
                   removefav.setVisibility(View.VISIBLE);
               }


            }else{
               addfav.setVisibility(View.VISIBLE);
               removefav.setVisibility(View.INVISIBLE);

           }
        } else {
            addfav.setVisibility(View.INVISIBLE);
            removefav.setVisibility(View.VISIBLE);


        }



        addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                List<Fav> fav=new ArrayList<>();
                long count2 = Fav.count(Fav.class);
                if(count2>-1)
                {
                    List<Fav> allfavs = Fav.listAll(Fav.class);
//                    if (allfavs.size()>0){

//                        fav= Fav.find(Fav.class, "churchId = ?", churchid);
                        Fav favadd = new Fav("",churchid);
//                        if (fav == null) {

                            addfav.setVisibility(View.VISIBLE);
                            removefav.setVisibility(View.INVISIBLE);

                            favadd.save();

//                        }

//                    }else{
//                        if (Fav.find(Fav.class, "church_Id = ?", churchid) == null) {
//
//                            addfav.setVisibility(View.VISIBLE);
//                            removefav.setVisibility(View.INVISIBLE);
//
//
//                        } else {
//                            addfav.setVisibility(View.INVISIBLE);
//                            removefav.setVisibility(View.VISIBLE);
////                        }
//                    }
                } else {
                    addfav.setVisibility(View.INVISIBLE);
                    removefav.setVisibility(View.VISIBLE);


                }









        }
        });

        removefav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addfav.setVisibility(View.VISIBLE);
                removefav.setVisibility(View.INVISIBLE);


                List<Fav> fav;
                long count = Fav.count(Fav.class);
                if(count>0)
                {

                    fav= Fav.find(Fav.class, "churchId = ?", churchid);


                    Fav favadd = new Fav(churchid,"");

                    if (fav == null) {

                        addfav.setVisibility(View.INVISIBLE);
                        removefav.setVisibility(View.VISIBLE);

                        favadd.delete();
                    }

                } else {
                    addfav.setVisibility(View.VISIBLE);
                    removefav.setVisibility(View.INVISIBLE);


                }

            }
        });



    }

    /** populates the schedules to list **/
    private void getschedule() {




            ScheduleAdapter scheduleAdapter = new ScheduleAdapter(churcscheduledata, this);
            scheduleList.setAdapter(scheduleAdapter);
            Utils.setListViewHeightBasedOnItems(scheduleList);



    }





    private void populatedatausingid(String churchid) {


        for( denominationchurchs a : churcdata) {
            if (a.getchId()==churchid) {

              churchimageurl= a.getDenochurchimageUrl();
                churchname=a.getNameChurchs();
                 churchphone=a.getChurchphone();
                 churchwebsite=a.getChurchwebsite();
                 churchmail=a.getChurchmail();
                 churchlocation=a.getLocation();
                 churchlongitude = a.getChurchlongitude();
                 churchlatitude=a.getChurchlatitude();

                populatedata(churchimageurl,churchname,churchphone,churchwebsite,churchmail,churchlocation,churchlongitude,churchlatitude);
            }
    }
    }

    private void populatedata(String churchimageurl,String churchname,String churchphone,String churchwebsite,String churchmail,
                              String churchlocation,String churchlongitude,String churchlatitude) {


        toolbar.setTitle(churchname);
        ChurchPhone.setText(churchphone);
        ChurchLink.setText(churchwebsite);
        ChurchEmail.setText(churchmail);
        Glide.with(context)
                .load(churchimageurl)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(ToolbarChurchImage);

        ChurchLongitude=churchlongitude;
        ChurchLattitude=churchlatitude;

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

    private static ArrayList<Schedules> getchurchScheduleList() {
        ArrayList<Schedules> list = new ArrayList<>();
        list.add(new Schedules("Sunday Surmon", "sunday", "10:00-7:00"));
        list.add(new Schedules("Wednsday worship", "wednsday", "10:00-7:00"));
        list.add(new Schedules("Youth", "sunday", "10:00-7:00"));

        return list;
    }

    public static ArrayList<eventchurchs> getchurchList() {
        ArrayList<eventchurchs> list = new ArrayList<>();
        list.add(new eventchurchs(1,"Yougo Blaze conference","10:30", "7/12/2017","Youth","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg","this is the event description","bole","38.828731","8.991639"));
        list.add(new eventchurchs(2,"beza Arise Africa","7:30","8/6/2017","Confrence","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg","this is the event description","bole","38.828731","8.991639"));
        list.add(new eventchurchs(3,"Greatcommison Outreach","5:30","5/12/2017","Gospel", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg","this is the event description","bole","38.828731","8.991639"));
        list.add(new eventchurchs(4,"Beki music consort","11:00","4/12/2017","Song Consort", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg","this is the event description","bole","38.828731","8.991639"));
        list.add(new eventchurchs(5,"Rinald Bonke Confrence","2:00","17/2/2017","Gospel", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg","this is the event description","bole","38.828731","8.991639"));
        return list;
    }


}
