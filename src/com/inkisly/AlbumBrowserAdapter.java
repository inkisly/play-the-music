package com.inkisly;

import com.inkisly.TrackBrowserAdapter.ViewHolder;
import com.inkisly.util.LogTrace;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class AlbumBrowserAdapter extends SimpleCursorAdapter {
	
	private Context mContext;
	
	static class ViewHolder {
		ImageView ivAlbumArt;
		TextView tvAlbumTitle;
		TextView tvArtist;
	};

	public AlbumBrowserAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		ViewHolder vh = (ViewHolder)view.getTag();
		
		String strAlbumArt = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Albums.ALBUM_ART ) );
		String strAlbumTitle = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Albums.ALBUM ) );
		String strArtist = cursor.getString( cursor.getColumnIndex( MediaStore.Audio.Albums.ARTIST ) );
		
		vh.tvAlbumTitle.setText( strAlbumTitle );
//		vh.tvAlbumTitle.setSelected( true );
		vh.tvArtist.setText( strArtist );
//		vh.tvArtist.setSelected( true );
		
		super.bindView(view, context, cursor);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
//		return super.newView(context, cursor, parent);
		View v = super.newView( context, cursor, parent );
		ViewHolder vh = new ViewHolder();
		
		vh.ivAlbumArt = (ImageView) v.findViewById( R.id.ivAlbumArt );
		vh.tvAlbumTitle = (TextView) v.findViewById( R.id.tvAlbumTitle );
		vh.tvArtist = (TextView) v.findViewById( R.id.tvArtist );
		
		v.setTag( vh );
		return v;
	}
}
