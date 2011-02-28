package com.inkisly;

import com.inkisly.util.LogTrace;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class TrackBrowser extends ListActivity {
	
	private Context mContext;
	
	Cursor mCursor = null;
	
	private ListView mlistMusicList;
	private TrackBrowserAdapter mMusicListAdapter;
	
	private String mArtistId;
	private String mAlbumId;
	private String mGenreId;
	private String mPlaylistId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView( R.layout.track_browser );
		mContext = getApplicationContext();
		
		Intent i = getIntent();
		mArtistId = i.getStringExtra( "artist" );
		mAlbumId = i.getStringExtra( "album" );
		mGenreId = i.getStringExtra( "genre" );
		mPlaylistId = i.getStringExtra( "playlist" );
		
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
		
		Intent i = new Intent();
		i.setAction( Intent.ACTION_VIEW );
		if ( mPlaylistId != null ) {
			int audio_id = mCursor.getInt( mCursor.getColumnIndex( MediaStore.Audio.Playlists.Members.AUDIO_ID ) );
			i.setDataAndType( Uri.withAppendedPath( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.toString( audio_id ) ), "audio/*" );
		} else {
			i.setDataAndType( Uri.withAppendedPath( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.toString( id ) ), "audio/*" );
		}
		startActivity( i );
		
		super.onListItemClick(l, v, position, id);
	}

	@Override
	public void setListAdapter(ListAdapter adapter) {
		// TODO Auto-generated method stub
		super.setListAdapter(adapter);
	}

	private void loadAllView() {
		// TODO Auto-generated method stub
//		mlistMusicList = getListView();
//		mlistMusicList = (ListView) findViewById( R.id.listMusicList );
//		mlistMusicList.setOnItemClickListener( mOnItemClickListener );
		
		searchList();
		this.setListAdapter( mMusicListAdapter );
//		mlistMusicList.setAdapter( mMusicListAdapter );
	}
	
	private void searchList() {
		ContentsDBManager cm = new ContentsDBManager( mContext );

        StringBuilder where = new StringBuilder();
        
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String [] projection = null;
		String selection = null;
		String [] selectionArgs = null;
		String sortOrder = null;		

		LogTrace.d("mGenreId : " + mGenreId );
		LogTrace.d("mPlaylistId : " + mPlaylistId );
		LogTrace.d("mAlbumId : " + mAlbumId );
		LogTrace.d("mArtistId : " + mArtistId );
		
		if ( mGenreId != null  ) {
			uri = MediaStore.Audio.Genres.Members.getContentUri( "external", Integer.valueOf( mGenreId ) );
		} else if ( mPlaylistId != null ) {
			uri = MediaStore.Audio.Playlists.Members.getContentUri( "external", Integer.valueOf( mPlaylistId ) );
		} else {
			uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
            where.append(MediaStore.Audio.Media.IS_MUSIC + " = 1");
			if ( mAlbumId != null ) {
				where.append( " AND " + MediaStore.Audio.Media.ALBUM_ID + " =? ");
				selectionArgs = new String[]{ mAlbumId };
			} else if ( mArtistId != null ) {
				where.append( " AND " + MediaStore.Audio.Media.ARTIST_ID + " =? ");
				selectionArgs = new String[]{ mArtistId };
			}
		}
        
		mCursor = cm.query(uri, projection, where.toString(), selectionArgs, sortOrder);
		
		startManagingCursor( mCursor );
		
		mMusicListAdapter = new TrackBrowserAdapter( mContext, R.layout.listitem_track_browser, mCursor, new String[]{}, new int[]{} );
	}
}
