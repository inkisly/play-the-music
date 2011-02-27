package com.inkisly;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
	private Context mContext;
	
	private Button mBtnAll;
    private Button mBtnBroser;

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
		mBtnBroser = (Button) findViewById( R.id.btnBrowser );
		mBtnBroser.setOnClickListener( mOnClickListener );
	}
	
	View.OnClickListener mOnClickListener = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent i;
			
			switch ( v.getId() ) {
			case R.id.btnAll:
				i = new Intent( mContext, MusicList.class );
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