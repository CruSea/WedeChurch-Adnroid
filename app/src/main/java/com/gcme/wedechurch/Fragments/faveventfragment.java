package com.gcme.wedechurch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.eventsadapter;
import com.gcme.wedechurch.adapters.faveventsadapter;
import com.gcme.wedechurch.model.eventchurchs;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class faveventfragment extends Fragment {

    private DynamicListView mDynamicListView;

    public faveventfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_faveventfragment, container, false);
        mDynamicListView = (DynamicListView) view.findViewById(R.id.fav_event_listview);
        // Inflate the layout for this fragment
        setUpSwipeToDissmissSocial();
        return view;
    }

    private void setUpSwipeToDissmissSocial() {
        final faveventsadapter adapter = new faveventsadapter(
                getActivity(), getchurchList());

        mDynamicListView.setAdapter(adapter);
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