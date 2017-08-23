package com.gcme.wedechurch.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.adapters.denominationchurchsadaptor;
import com.gcme.wedechurch.font.RobotoTextView;
import com.gcme.wedechurch.model.denominationchurchs;
import com.nhaarman.listviewanimations.itemmanipulation.DynamicListView;
import java.util.ArrayList;

import static com.gcme.wedechurch.Fragments.MapFragment.context;

public class selectedDenomination extends AppCompatActivity {


    private DynamicListView mDynamicListView;
    ImageView denoimage;
//     imageurl,denoname,denoemail,denophone;
    RobotoTextView Denomination_name,Denomination_contact_email,Denomination_contact_phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_denomination);
        mDynamicListView = (DynamicListView) findViewById(R.id.dynamic_listview);
        mDynamicListView.setDividerHeight(0);
        denoimage = (ImageView) findViewById(R.id.denomination_main_image);
        Denomination_name= (RobotoTextView) findViewById(R.id.Denomination_name);
        Denomination_contact_email= (RobotoTextView) findViewById(R.id.Denomination_contact_email);
        Denomination_contact_phone= (RobotoTextView) findViewById(R.id.Denomination_contact_phone);

        Intent intent = getIntent();
        Bundle bd = intent.getExtras();


        if(bd != null)
        {
            String imageurl = (String) bd.get("denoImageurl");
            String denoname = (String) bd.get("denoName");
            String  denoemail = (String) bd.get("denoemail");
            String denophone = (String) bd.get("denophone");
            populatedata(imageurl,denoname,denoemail,denophone);
        }


        mDynamicListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent=new Intent(selectedDenomination.this,singleChurchDetail.class);
                intent.putExtra("","");
                startActivity(intent);

            }
        });

        populatechurchs();

    }

    private void populatedata(String imageurl, String denoname, String denoemail, String denophone) {

        Glide.with(context)
                .load(imageurl)
                .asBitmap()
                .placeholder(R.mipmap.app_logo_png)
                .into(denoimage);
        Denomination_name.setText(denoname);
        Denomination_contact_email.setText(denoemail);
        Denomination_contact_phone.setText(denophone);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu_black_icon, menu);
        return true;
    }

    private void populatechurchs() {
        final denominationchurchsadaptor adapter = new denominationchurchsadaptor(
                this, getchurchList());
        mDynamicListView.setAdapter(adapter);

    }


    private static ArrayList<denominationchurchs> getchurchList() {
        ArrayList<denominationchurchs> list = new ArrayList<>();

        list.add(new denominationchurchs("1","Bole MKC","Bole", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/0.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("2","Yeka MKC","Yeka","http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/1.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("3","Kazanchis MKC","Kazanchis", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/2.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("4","Semit MKC","Semit", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/3.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));
        list.add(new denominationchurchs("5","Piassa MKC","Piassa", "http://pengaja.com/uiapptemplate/newphotos/listviews/draganddrop/travel/4.jpg", "0913609212","bolemkc.com","bolemkc@bolemkc.info","38.828731","8.991639"));

        return list;
    }


}
