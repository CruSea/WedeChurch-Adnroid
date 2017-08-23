package com.gcme.wedechurch.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcme.wedechurch.activities.EventDetail;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.model.eventchurchs;
import java.util.ArrayList;

public class eventsadapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<eventchurchs> mDummyModelList;

	public eventsadapter(Context context,
                         ArrayList<eventchurchs> dummyModelList) {
		mContext = context;
		mInflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		mDummyModelList = dummyModelList;

	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public int getCount() {
		return mDummyModelList.size();
	}

	@Override
	public Object getItem(int position) {
		return mDummyModelList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return mDummyModelList.get(position).getmId();
	}



	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;

				convertView = mInflater.inflate(
						R.layout.events_item, parent,
						false);
				holder = new ViewHolder();
				holder.eventitemholder = (RelativeLayout) convertView
						.findViewById(R.id.eventitemholder);
				holder.eventimage = (ImageView) convertView
						.findViewById(R.id.eventimage);
				holder.travelHeader = (TextView) convertView
						.findViewById(R.id.eventtitle);
				holder.eventdate = (TextView) convertView
						.findViewById(R.id.eventdate);
				holder.eventtime = (TextView) convertView
				.findViewById(R.id.eventtime);
				holder.eventcategory = (TextView) convertView
				.findViewById(R.id.eventcategory);

			convertView.setTag(holder);


		final eventchurchs dm= mDummyModelList.get(position);

			String eventurl = dm.getEventimageurl();
			Glide.with(mContext)
				.load(eventurl)
				.asBitmap()
				.placeholder(R.mipmap.app_logo_png)
				.into(holder.eventimage);
		     holder.travelHeader.setText(dm.getNameevent());
		     holder.eventdate.setText(dm.getEventdate());
		     holder.eventtime.setText(dm.getEventtime());
		     holder.eventcategory.setText(dm.getEventcategory());
			// holder.travelHeader.setText(dm.getText());

			holder.eventitemholder.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent=new Intent(mContext, EventDetail.class);
					//add data to the Intent object
					intent.putExtra("id", dm.getmId());
					intent.putExtra("name", dm.getNameevent());
					intent.putExtra("image", dm.getEventimageurl());
					intent.putExtra("desc", dm.getEventdescription());
					intent.putExtra("date", dm.getEventdate());
					intent.putExtra("time", dm.getEventtime());
					intent.putExtra("category", dm.getEventcategory());
					intent.putExtra("location", dm.getEventlocation());
					intent.putExtra("longitude", dm.getEventlongitude());
					intent.putExtra("latitude", dm.getEventlatitude());
					//start the second activity
					mContext.startActivity(intent);
				}
			});

			return convertView;

	}

	private static class ViewHolder {
		public ImageView eventimage;
		public/* Roboto */TextView text;
		public/* Roboto */TextView name;
		public/* Roboto */TextView travelHeader;
		public/* Roboto */TextView eventdate;
		public/* Fontello */TextView icon;
		public/* Fontello */TextView eventtime;
		public/* Fontello */TextView eventcategory;
		public RelativeLayout eventitemholder;
	}




	public void remove(int position) {
		mDummyModelList.remove(position);
	}
}
