package com.gcme.wedechurch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.denominationchurchsadaptor;
import com.gcme.wedechurch.model.denominationchurchs;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class favchurchfragment extends Fragment {

    private DynamicListView mDynamicListView;
    public favchurchfragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favchurchfragment, container, false);
        mDynamicListView = (DynamicListView) view.findViewById(R.id.fav_listview_churchs);
        mDynamicListView.setDividerHeight(0);
        populatechurchs();
        return view;
    }
    private void populatechurchs() {
        final denominationchurchsadaptor adapter = new denominationchurchsadaptor(
                getActivity(), getchurchList());
        mDynamicListView.setAdapter(adapter);

    }

    public static ArrayList<denominationchurchs> getchurchList() {
        ArrayList<denominationchurchs> list = new ArrayList<>();

        list.add(new denominationchurchs(1,"Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg"));
        list.add(new denominationchurchs(2,"Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg"));
        list.add(new denominationchurchs(3,"Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg"));
        list.add(new denominationchurchs(4,"Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg"));
        list.add(new denominationchurchs(5,"Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg"));

        return list;
    }

}
