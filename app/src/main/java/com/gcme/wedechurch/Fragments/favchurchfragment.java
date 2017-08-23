package com.gcme.wedechurch.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.denominationchurchsadaptor;
import com.gcme.wedechurch.adapters.favchurchsadaptor;
import com.gcme.wedechurch.model.Fav;
import com.gcme.wedechurch.model.denominationchurchs;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;

import java.util.ArrayList;
import java.util.List;

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

        List<Fav> allContacts = Fav.listAll(Fav.class);
        ArrayList<denominationchurchs> arr = new ArrayList<>();


        for(Fav fav:allContacts){

            List<denominationchurchs> singlechurch = denominationchurchs.findWithQuery(denominationchurchs.class, "Select * from denominationchurchs where id = ?", fav.getChurchId());
            arr.add((denominationchurchs) singlechurch);
        }

        final favchurchsadaptor adapter = new favchurchsadaptor(
                getActivity(), arr);
        mDynamicListView.setAdapter(adapter);

    }

//    private static ArrayList<denominationchurchs> getchurchList() {
//        ArrayList<denominationchurchs> list = new ArrayList<>();
//
//        list.add(new denominationchurchs("1","Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
//        list.add(new denominationchurchs("2","Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
//        list.add(new denominationchurchs("3","Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
//        list.add(new denominationchurchs("4","Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
//        list.add(new denominationchurchs("5","Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
//
//        return list;
//    }

}
