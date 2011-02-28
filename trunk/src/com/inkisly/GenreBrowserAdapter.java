package com.inkisly;

import android.content.Context;
import android.database.Cursor;
import android.widget.SimpleCursorAdapter;

public class GenreBrowserAdapter extends SimpleCursorAdapter {
	
	private Context mContext;

	public GenreBrowserAdapter(Context context, int layout, Cursor c,
			String[] from, int[] to) {
		super(context, layout, c, from, to);
		// TODO Auto-generated constructor stub
		mContext = context;
	}

}
