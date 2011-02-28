package com.inkisly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
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
		mGridAlbumList.setOnItemClickListener( mOnItemCliclListener );
		
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
	
	AdapterView.OnItemClickListener mOnItemCliclListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
	        Intent intent = new Intent(Intent.ACTION_PICK);
	        intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/track");
	        intent.putExtra("album", Long.valueOf(id).toString());
//	        intent.putExtra("artist", mArtistId);
	        startActivity(intent);
		}
	};
}
