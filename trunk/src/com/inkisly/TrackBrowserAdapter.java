package com.inkisly;


import com.inkisly.util.Util;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class TrackBrowserAdapter extends SimpleCursorAdapter {
	
	private Context mContext;
	
	static class ViewHolder {
		TextView tvTitle;
		TextView tvArtist;
		TextView tvDuration;
	};

	public TrackBrowserAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder vh = (ViewHolder)view.getTag();
		
		String title = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.TITLE ) );
		String artist = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Media.ARTIST ) );
		long secs = cursor.getInt( cursor.getColumnIndex( MediaStore.Audio.Media.DURATION ) );
		String duration = Util.makeTimeString( mContext, secs );
		
		vh.tvTitle.setText( title );
		vh.tvArtist.setText( artist );
		vh.tvDuration.setText( duration );
		
		super.bindView(view, context, cursor);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.newView(context, cursor, parent);
		View v = super.newView( context, cursor, parent );
		ViewHolder vh = new ViewHolder();
		
		vh.tvTitle = (TextView) v.findViewById( R.id.tvTitle );
		vh.tvArtist = (TextView) v.findViewById( R.id.tvArtist );
		vh.tvDuration = (TextView) v.findViewById( R.id.tvDuration );
		
		v.setTag( vh );
		return v;
	}
}
