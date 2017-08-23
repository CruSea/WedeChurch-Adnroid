package com.gcme.wedechurch.activities;


import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;

public class EventDetail extends AppCompatActivity {

    TextView title, sourcedetail, descrip,event_location,event_date,event_time;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_details);
        Toolbar Detailtoolbar= (Toolbar) findViewById(R.id.Eventdetailtoolbar);
        TextView descrip = (TextView) findViewById(R.id.detail_event_description);
        TextView event_location = (TextView) findViewById(R.id.event_location);
        TextView event_date = (TextView) findViewById(R.id.event_date);
        TextView event_type = (TextView) findViewById(R.id.eventtype);
        TextView event_time = (TextView) findViewById(R.id.event_time);
        ImageView image = (ImageView) findViewById(R.id.detail_event_image);
        final ImageButton delete_event = (ImageButton) findViewById(R.id.delete_event);
        final ImageButton add_event = (ImageButton) findViewById(R.id.add_event);
        ImageButton share_event = (ImageButton) findViewById(R.id.share_event);

        String feed = getIntent().getExtras().getString("name");
        String source = getIntent().getExtras().getString("id");
        String detdesc = getIntent().getExtras().getString("desc");
        String bitmap = getIntent().getStringExtra("image");
        String date = getIntent().getStringExtra("date");
        String time = getIntent().getStringExtra("time");
        String category = getIntent().getStringExtra("category");
        String location = getIntent().getStringExtra("location");

        String longitude = getIntent().getStringExtra("longitude");
        String latitude = getIntent().getStringExtra("latitude");

        delete_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_event.setVisibility(View.VISIBLE);
                delete_event.setVisibility(View.INVISIBLE);
            }
        });

        add_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                delete_event.setVisibility(View.VISIBLE);
                add_event.setVisibility(View.INVISIBLE);
            }
        });

        share_event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });



        Glide.with(this)
                .load(bitmap)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(image);
        descrip.setText(detdesc);
        Detailtoolbar.setTitle(feed);
        event_location.setText(location);
        event_date.setText(date);
        event_time.setText(time);
        event_type.setText(category);


    }

}
