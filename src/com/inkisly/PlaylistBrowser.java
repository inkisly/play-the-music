package com.inkisly;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ListView;

public class PlaylistBrowser extends ListActivity {

	private Context mContext;
	private PlaylistBrowserAdapter mPlaylistBrowserAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView( R.layout.playlist_browser );
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

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/track");
        intent.putExtra("playlist", Long.valueOf(id).toString());
//        intent.putExtra("artist", mArtistId);
        startActivity(intent);
        
		super.onListItemClick(l, v, position, id);
	}

	private void loadAllView() {
		// TODO Auto-generated method stub
		searchList();
		setListAdapter( mPlaylistBrowserAdapter );
	}
	
	private void searchList() {
		ContentsDBManager cm = new ContentsDBManager( mContext );
		
		Uri uri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
		String [] projection = null;
		String selection = null;
		String [] selectionArgs = null;
		String sortOrder = null;		
		
		Cursor c = cm.query(uri, projection, selection, selectionArgs, sortOrder);
		startManagingCursor( c );
		
		mPlaylistBrowserAdapter = new PlaylistBrowserAdapter( mContext, R.layout.listitem_playlist_browser, c, new String[]{}, new int[]{} );
	}
}
