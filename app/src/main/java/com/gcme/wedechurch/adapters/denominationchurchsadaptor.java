package com.gcme.wedechurch.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.activities.selectedDenomination;
import com.gcme.wedechurch.activities.singleChurchDetail;
import com.gcme.wedechurch.model.denominationchurchs;

import java.util.ArrayList;
import java.util.Collections;

public class denominationchurchsadaptor extends BaseAdapter  {

	private Context mContext;
	private LayoutInflater mInflater;
	private ArrayList<denominationchurchs> mDummyModelList;
	private String category;

	public denominationchurchsadaptor(Context context,
                                      ArrayList<denominationchurchs> dummyModelList) {
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
		return Long.parseLong(mDummyModelList.get(position).getchId());
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;


				convertView = mInflater.inflate(
						R.layout.churchs_list, parent, false);
				holder = new ViewHolder();
				holder.image = (ImageView) convertView
						.findViewById(R.id.churchimage);
				holder.churchname = (TextView) convertView
						.findViewById(R.id.churchname);
				holder.churchlocation = (TextView) convertView
						.findViewById(R.id.churchlocation);
				holder.churchlistholder = (LinearLayout) convertView
						.findViewById(R.id.churchlistholder);




			final denominationchurchs dm = mDummyModelList.get(position);
		    String url = dm.getDenochurchimageUrl();
		    Glide.with(mContext)
				.load(url)
				.asBitmap()
				.placeholder(R.mipmap.app_logo_png)
				.into(holder.image);
			holder.churchname.setText(dm.getNameChurchs());

			holder.churchlocation.setText(dm.getLocation());

		holder. churchlistholder .setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {



				passdata(Integer.parseInt(dm.getchId()));



			}
		});


		return convertView;
	}
	private void passdata(int churchId) {

		Intent i=new Intent(mContext,singleChurchDetail.class);
		i.putExtra("selectedchurchid",churchId);

		mContext.startActivity(i);
	}

	private static class ViewHolder {
		public ImageView image;
		public LinearLayout churchlistholder;
		public/* Roboto */TextView churchname;
		public/* Roboto */TextView churchlocation;
	}
}
