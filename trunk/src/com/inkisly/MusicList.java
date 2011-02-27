package com.inkisly;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MusicList extends ListActivity {
	
	private Context mContext;
	
	private ListView mlistMusicList;
	private MusicListAdapter mMusicListAdapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView( R.layout.music_list );
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
		
		Intent i = new Intent();
		i.setAction( Intent.ACTION_VIEW );
		i.setDataAndType( Uri.withAppendedPath( MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, Long.toString( id ) ), "audio/*" );
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
		
		Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
		String [] projection = null;
		String selection = MediaStore.Audio.Media.IS_MUSIC + " = ? ";
		String [] selectionArgs = new String[]{ "1" };
		String sortOrder = null;		
		
		Cursor c = cm.query(uri, projection, selection, selectionArgs, sortOrder);
		startManagingCursor( c );
		
		mMusicListAdapter = new MusicListAdapter( mContext, R.layout.listitem_music_list, c, new String[]{}, new int[]{} );
	}
}
