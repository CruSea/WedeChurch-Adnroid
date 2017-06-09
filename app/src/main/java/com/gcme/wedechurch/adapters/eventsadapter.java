package com.gcme.wedechurch.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.model.denominationchurchs;
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
			eventchurchs dm = mDummyModelList.get(position);

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
	}




	public void remove(int position) {
		mDummyModelList.remove(position);
	}
}
