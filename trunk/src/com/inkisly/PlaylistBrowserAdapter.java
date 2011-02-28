package com.inkisly;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class PlaylistBrowserAdapter extends SimpleCursorAdapter {
	
	private Context mContext;
	
	static class ViewHolder {
		TextView tvPlaylistTitle;
	};

	public PlaylistBrowserAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder vh = (ViewHolder)view.getTag();
		
		String playlist = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Playlists.NAME ) );
		
		vh.tvPlaylistTitle.setText( playlist );
		
		super.bindView(view, context, cursor);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.newView(context, cursor, parent);
		View v = super.newView( context, cursor, parent );
		ViewHolder vh = new ViewHolder();
		
		vh.tvPlaylistTitle = (TextView) v.findViewById( R.id.tvPlaylistTitle );
		
		v.setTag( vh );
		return v;
	}

}
