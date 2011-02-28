package com.inkisly;


import android.app.ExpandableListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ExpandableListView;

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

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
        String mCurrentAlbumId = Long.valueOf(id).toString();
        
		Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/track");
        intent.putExtra("album", mCurrentAlbumId);
        Cursor c = (Cursor) getExpandableListAdapter().getChild(groupPosition, childPosition);
        String album = c.getString(c.getColumnIndex(MediaStore.Audio.Albums.ALBUM));
        if (album == null || album.equals(MediaStore.UNKNOWN_STRING)) {
            // unknown album, so we should include the artist ID to limit the songs to songs only by that artist 
//            mArtistCursor.moveToPosition(groupPosition);
//            mCurrentArtistId = mArtistCursor.getString(mArtistCursor.getColumnIndex(MediaStore.Audio.Artists._ID));
//            intent.putExtra("artist", mCurrentArtistId);
        }
        startActivity(intent);
		return super.onChildClick(parent, v, groupPosition, childPosition, id);
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
