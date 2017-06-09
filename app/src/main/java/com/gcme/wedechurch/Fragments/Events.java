package com.gcme.wedechurch.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.eventsadapter;
import com.gcme.wedechurch.model.denominationchurchs;
import com.gcme.wedechurch.model.eventchurchs;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import java.util.ArrayList;

public class Events extends Fragment {
    private DynamicListView mDynamicListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_events, container, false);
        mDynamicListView = (DynamicListView) view.findViewById(R.id.event_listview);
        // Inflate the layout for this fragment
        setUpSwipeToDissmissSocial();
        return view;
    }

    private void setUpSwipeToDissmissSocial() {
        final eventsadapter adapter = new eventsadapter(
                getActivity(), getchurchList());

        mDynamicListView.setAdapter(adapter);
    }


    public static ArrayList<eventchurchs> getchurchList() {
        ArrayList<eventchurchs> list = new ArrayList<>();
        list.add(new eventchurchs(1,"Yougo Blaze conference","10:30", "7/12/2017","Youth","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg"));
        list.add(new eventchurchs(2,"beza Arise Africa","7:30","8/6/2017","Confrence","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg"));
        list.add(new eventchurchs(3,"Greatcommison Outreach","5:30","5/12/2017","Gospel", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg"));
        list.add(new eventchurchs(4,"Beki music consort","11:00","4/12/2017","Song Consort", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg"));
        list.add(new eventchurchs(5,"Rinald Bonke Confrence","2:00","17/2/2017","Gospel", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg"));
        return list;
    }


}



