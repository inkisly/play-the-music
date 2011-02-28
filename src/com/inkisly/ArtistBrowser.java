package com.inkisly;


import android.app.ExpandableListActivity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

public class ArtistBrowser extends ExpandableListActivity {

	private Context mContext;
	private ArtistBrowserAdapter mArtistBrowserAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView( R.layout.artist_browser );
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
		searchList();
		getExpandableListView().setGroupIndicator( null );
		setListAdapter( mArtistBrowserAdapter );
	}
	
	private void searchList() {
		ContentsDBManager cm = new ContentsDBManager( mContext );
		
		Uri uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
		String [] projection = null;
		String selection = null;
		String [] selectionArgs = null;
		String sortOrder = null;		
		
		Cursor c = cm.query(uri, projection, selection, selectionArgs, sortOrder);
		startManagingCursor( c );
		
		mArtistBrowserAdapter = new ArtistBrowserAdapter( 
				mContext, 
				c, 
				R.layout.listitem_artist_browser, 
				new String[]{}, 
				new int[]{},
				R.layout.listitem_album_browser,
				new String[]{}, 
				new int[]{}
				);
	}
}
