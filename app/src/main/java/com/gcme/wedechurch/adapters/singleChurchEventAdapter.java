package com.gcme.wedechurch.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.gcme.wedechurch.R;
import com.gcme.wedechurch.activities.EventDetail;
import com.gcme.wedechurch.model.eventchurchs;

import java.util.List;

public class singleChurchEventAdapter extends RecyclerView.Adapter<singleChurchEventAdapter.MyViewHolder> {

	private LayoutInflater mInflater;
	Activity context;
	private List<eventchurchs> feedsList;

	public singleChurchEventAdapter(Activity context, List<eventchurchs> items) {

		this.context=context;
		this.feedsList = items;
		mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

		View rootView = mInflater.inflate(R.layout.church_events_item, parent, false);
		return new MyViewHolder(rootView);
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position) {
		final eventchurchs feeds = feedsList.get(position);

		String url = feeds.getEventimageurl();
		Glide.with(context)
				.load(url)
				.asBitmap()
				.placeholder(R.drawable.wedechurch_icon )
				.into(holder.image);


		//Pass the values of feeds object to Views

		holder.text.setText(feeds.getNameevent());
		holder.typetext.setText(feeds.getEventcategory());
//        holder.numberofchurches.setText((feeds.getnumber()));
//        File file = new File(feeds.getImageLocation());
		holder. image .setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {

				Intent intent=new Intent(context, EventDetail.class);
				//add data to the Intent object
				intent.putExtra("id", feeds.getmId());
				intent.putExtra("name", feeds.getNameevent());
				intent.putExtra("image", feeds.getEventimageurl());
				intent.putExtra("desc", feeds.getEventdescription());
				intent.putExtra("date", feeds.getEventdate());
				intent.putExtra("time", feeds.getEventtime());
				intent.putExtra("category", feeds.getEventcategory());
				intent.putExtra("location", feeds.getEventlocation());
				intent.putExtra("longitude", feeds.getEventlongitude());
				intent.putExtra("latitude", feeds.getEventlatitude());
				//start the second activity
				context.startActivity(intent);




			}
		});
	}


	@Override
	public int getItemCount() {
		return feedsList.size();
	}

	public class MyViewHolder extends RecyclerView.ViewHolder {

		public ImageView image;
		//		public TextView title;
		public TextView text,typetext;
		public MyViewHolder(View itemView) {
			super(itemView);


			image = (ImageView) itemView
					.findViewById(R.id.list_item_google_cards_travel_image);

			text = (TextView) itemView
					.findViewById(R.id.list_item_google_cards_travel_text);
			typetext = (TextView) itemView
					.findViewById(R.id.church_event_date);



		}
	}

}


