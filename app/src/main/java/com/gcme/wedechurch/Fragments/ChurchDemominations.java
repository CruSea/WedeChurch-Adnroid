package com.gcme.wedechurch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.wedechurch.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChurchDemominations extends Fragment {


    public ChurchDemominations() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_church_demominations, container, false);
    }

}
