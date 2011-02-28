package com.inkisly;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import com.inkisly.util.LogTrace;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils.TruncateAt;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class FileBrowser extends Activity {

	private Context mContext;

	private File root;
	private File current;

	private ListView mListBroser;
	private FileBrowserAdapter mFileBrowserAdapter;
	private ArrayList<FileBrowserItem> mFileBrowserItemsList = new ArrayList<FileBrowserItem>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.file_browser);
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
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if (current != null
					&& !current.getParent().equals(root.getParent())) {
				setFileBrowser(current.getParentFile());
				mFileBrowserAdapter.notifyDataSetChanged();
				return false;
			}
			break;

		default:
			break;
		}
		return super.onKeyDown(keyCode, event);
	}

	private void loadAllView() {
		// TODO Auto-generated method stub
		TextView title = (TextView) findViewById(android.R.id.title); 
		// set the ellipsize mode to MARQUEE and make it scroll only once 
		title.setEllipsize(TruncateAt.MARQUEE);
		title.setMarqueeRepeatLimit( -1 ); 
		title.setSelected( true );
		
		mListBroser = (ListView) findViewById(R.id.listBrowser);
		mListBroser.setOnItemClickListener(mOnItemClickListener);

		mFileBrowserAdapter = new FileBrowserAdapter(mContext,
				R.layout.listitem_file_browser, 0, mFileBrowserItemsList);
		mListBroser.setAdapter(mFileBrowserAdapter);

		setFileBrowser(null);
	}

	private File[] fileBrowser(File file) {
		// TODO Auto-generated method stub
		File f;
		if (file == null) {
			root = f = Environment.getExternalStorageDirectory();
		} else {
			current = f = file;
		}
	
		setTitle( f.getPath() );
		
		File[] listFile = f.listFiles();

		// for( int i=0; i<listFile.length; i++ ) {
		// LogTrace.d("listFile getName : " + listFile[i].getName() );
		// LogTrace.d("listFile isFile : " + listFile[i].isFile() );
		// LogTrace.d("listFile isDirectory : " + listFile[i].isDirectory() );
		// }

		return listFile;
	}

	private void setFileBrowser(File file) {
		
		mFileBrowserItemsList.clear();
		
		File[] listFile = fileBrowser(file);

		Arrays.sort( listFile, new FileSort().new FileNameSort() );
		Arrays.sort( listFile, new FileSort().new FileDirectorySort() );

		if (listFile != null) {

			for (int i = 0; i < listFile.length; i++) {

				if ( listFile[i].isHidden() ) continue;
				
				boolean isFile = false;
				boolean isDirectory = false;
				String strFileName = null;
				String strAbsolutePath = null;
				long lLastModified = 0;

				if (listFile[i].isFile()) {
					isFile = listFile[i].isFile();
					// isFile = fileFilter.accept( listFile[i] );
				}

				isDirectory = listFile[i].isDirectory();
				strFileName = listFile[i].getName();
				strAbsolutePath = listFile[i].getAbsolutePath();
				lLastModified = listFile[i].lastModified();

				FileBrowserItem item = new FileBrowserItem(isFile, isDirectory,
						strFileName, strAbsolutePath, lLastModified);
				mFileBrowserItemsList.add(item);
			}
		}
	}

	AdapterView.OnItemClickListener mOnItemClickListener = new AdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
			LogTrace.d("id : " + id);
			LogTrace.d("position : " + position);

			FileBrowserItem item = mFileBrowserAdapter.getItem(position);

			boolean isFile = item.isFile();
			String path = item.getStrAbsolutePath();

			LogTrace.d("path : " + path);
			LogTrace.d("isFile : " + isFile);
			LogTrace.d("isDirectory : " + item.isDirectory());

			File file = new File(path);

			if (isFile) {
				Intent i = new Intent();
				i.setAction(Intent.ACTION_VIEW);
				i.setDataAndType(Uri.fromFile(file), "audio/*");
				startActivity(i);
				return;
			}

			if (!item.isDirectory())
				return;

			setFileBrowser(file);
			mFileBrowserAdapter.notifyDataSetChanged();
		}
	};
}
