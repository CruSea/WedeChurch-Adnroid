package com.gcme.wedechurch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.ViewPagerAdapter;
import com.gcme.wedechurch.adapters.fabTabLayoutadapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class favorites extends Fragment {
    ViewPager pager;
    ViewPagerAdapter adapter;
    fabTabLayoutadapter tabs;
    CharSequence Titles[] = {"My Church","Favorite Churches", "Event Reminder"};
    int Numboftabs = 3;

    public favorites() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_favorites, container, false);
        adapter = new ViewPagerAdapter(getFragmentManager(), Titles, Numboftabs);

        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        // Assiging the Sliding Tab Layout View
        tabs = (fabTabLayoutadapter) view.findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width

        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new fabTabLayoutadapter.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.colorAccent);
            }
        });

        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);

        return view;
    }

}
