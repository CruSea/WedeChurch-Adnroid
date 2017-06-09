package com.gcme.wedechurch.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gcme.wedechurch.model.Denomination;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.activities.selectedDenomination;

import java.util.List;

/**
 * Created by vamsi on 06-May-16 for android recyclerview and cardview tutorial
 */
public class DenominationAdapter extends RecyclerView.Adapter<DenominationAdapter.ViewHolder> {

    private List<Denomination> churchList;
    private Context context;
    String url;


    //Provide a reference to the views for each data item
    //Complex data items may need more than one view per item, and
    //you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //each data item is just a string in this case
        public TextView churchNumber,denominationName;
        public ImageView imageurl;


        public ViewHolder(View v) {
            super(v);
            denominationName = (TextView) v.findViewById(R.id.denochurchname);
//            churchNumber = (TextView) v.findViewById(R.id.numberofchurches);
            imageurl = (ImageView) v.findViewById(R.id.denomination_thumbnail);

        }
    }

    //Provide a suitable constructor
    public DenominationAdapter(Context context, List<Denomination> songList){
        this.churchList = songList;
        this.context = context;
    }

    //Create new views (invoked by the layout manager)
    @Override
    public DenominationAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //Creating a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.deno_church_list,parent,false);

        //set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    //Replace the contents of a view (invoked by the layout manager
    @Override
    public void onBindViewHolder(DenominationAdapter.ViewHolder holder, int position) {

        // - get element from arraylist at this position
        // - replace the contents of the view with that element


        final Denomination song = churchList.get(position);
        holder.denominationName.setText(song.getDenominationName());
        url = song.getDenoImageUrl();
        Glide.with(context)
                .load(url)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(holder.imageurl);

        holder. imageurl .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



//                Cursor cursor = DbHelper.getSelectedRows();
//
//
//                String rowId = cursor.getString(cursor.getColumnIndex(DbHelper.CATEGORY));
passdata(song.getDenoImageUrl(),song.getDenominationName(),song.getDenoemail(),song.getDenophone());


//                android.support.v4.app.Fragment fragment = new ChurchDenomination();
//                android.support.v4.app.FragmentManager manager= allchurches.getActivity().getSupportFragmentManager();
//                Bundle args = new Bundle();
//                args.putString("denominations",feeds.getFeedName());
//                fragment .setArguments(args);
//
//                android.support.v4.app.FragmentTransaction transaction =   manager.beginTransaction();
//                transaction.replace(R.id.fragment_container,fragment);
//                transaction.addToBackStack("tag_back_Events");
//                transaction.commit();

            }
        });


    }

    private void passdata(String denoImageurl,String denoName,String denoemail,String denophone) {

        Intent i=new Intent(context,selectedDenomination.class);
        i.putExtra("denoImageurl",denoImageurl);
        i.putExtra("denoName",denoName);
        i.putExtra("denoemail",denoemail);
        i.putExtra("denophone",denophone);
        context.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return churchList.size();
    }


}