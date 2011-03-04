package com.inkisly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
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
    private Button mBtnCoverFlow;


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
		mBtnCoverFlow = (Button) findViewById( R.id.btnCoverFlow );
		mBtnCoverFlow.setOnClickListener( mOnClickListener );
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub

	        Intent intent = new Intent(Intent.ACTION_PICK);
			
			switch ( v.getId() ) {
			case R.id.btnAll:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/track");
				break;
				
			case R.id.btnArtist:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/artistalbum");
				break;
				
			case R.id.btnAlbum:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/album");
				break;
				
			case R.id.btnPlaylist:
                intent.setDataAndType(Uri.EMPTY, MediaStore.Audio.Playlists.CONTENT_TYPE);
				break;
				
			case R.id.btnGenre:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/genre");
				break;
				
			case R.id.btnBrowser:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/files");
				break;

			case R.id.btnCoverFlow:
                intent.setDataAndType(Uri.EMPTY, "vnd.android.cursor.dir/coverflow");
				break;
				
			default:
				break;
			}

	        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        startActivity(intent);
		}
	};
}