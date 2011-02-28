package com.inkisly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Artists;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Context mContext;
	
	private Button mBtnAll;
	private Button mBtnArtist;
	private Button mBtnAlbum;
	private Button mBtnPlaylist;
	private Button mBtnGenre;
    private Button mBtnBrowser;


	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = getApplicationContext();
        loadAllView();
    }

	private void loadAllView() {
		// TODO Auto-generated method stub
		mBtnAll = (Button) findViewById( R.id.btnAll );
		mBtnAll.setOnClickListener( mOnClickListener );
		mBtnArtist = (Button) findViewById( R.id.btnArtist );
		mBtnArtist.setOnClickListener( mOnClickListener );
		mBtnAlbum = (Button) findViewById( R.id.btnAlbum );
		mBtnAlbum.setOnClickListener( mOnClickListener );
		mBtnPlaylist = (Button) findViewById( R.id.btnPlaylist );
		mBtnPlaylist.setOnClickListener( mOnClickListener );
		mBtnGenre = (Button) findViewById( R.id.btnGenre );
		mBtnGenre.setOnClickListener( mOnClickListener );
		mBtnBrowser = (Button) findViewById( R.id.btnBrowser );
		mBtnBrowser.setOnClickListener( mOnClickListener );
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent i;
			
			switch ( v.getId() ) {
			case R.id.btnAll:
				i = new Intent( mContext, TrackBrowser.class );
				startActivity( i );
				break;
				
			case R.id.btnArtist:
				i = new Intent( mContext, ArtistBrowser.class );
				startActivity( i );
				break;
				
			case R.id.btnAlbum:
				i = new Intent( mContext, AlbumBrowser.class );
				startActivity( i );
				break;
				
			case R.id.btnPlaylist:
				i = new Intent( mContext, PlaylistBrowser.class );
				startActivity( i );
				break;
				
			case R.id.btnGenre:
				i = new Intent( mContext, GenreBrowser.class );
				startActivity( i );
				break;
				
			case R.id.btnBrowser:
				i = new Intent( mContext, FileBrowser.class );
				startActivity( i );
				break;

			default:
				break;
			}
		}
	};
}