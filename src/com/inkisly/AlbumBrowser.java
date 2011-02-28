package com.inkisly;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.GridView;

public class AlbumBrowser extends Activity {
	
	private Context mContext;
	
	private GridView mGridAlbumList;
	private AlbumBrowserAdapter mAlbumBrowserAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView( R.layout.album_browser );
		mContext = getApplicationContext();
		loadAllView();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	private void loadAllView() {
		// TODO Auto-generated method stub
		mGridAlbumList = (GridView) findViewById( R.id.gridAlbumList );
		
		searchList();
		mGridAlbumList.setAdapter( mAlbumBrowserAdapter );
	}

	private void searchList() {
		ContentsDBManager cm = new ContentsDBManager( mContext );
		
		Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
		String [] projection = new String[] {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ARTIST,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.ALBUM_ART
        };
		String selection = null;
		String [] selectionArgs = null;
		String sortOrder = null;		
		
		Cursor c = cm.query(uri, projection, selection, selectionArgs, sortOrder);
		startManagingCursor( c );
		
		mAlbumBrowserAdapter = new AlbumBrowserAdapter( mContext, R.layout.listitem_album_browser_grid, c, new String[]{}, new int[]{} );
	}

}
