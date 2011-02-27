package com.inkisly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.w3c.dom.Text;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileBrowserAdapter extends ArrayAdapter<FileBrowserItem>{
	
	private Context mContext;
	private ArrayList<FileBrowserItem> mFileBrowserItem;
	
	public FileBrowserAdapter(Context context, int resource,
			int textViewResourceId, ArrayList<FileBrowserItem> arraylist) {
		super(context, resource, textViewResourceId, arraylist);
		// TODO Auto-generated constructor stub
		mContext = context;
		mFileBrowserItem = arraylist;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if ( v == null )
		{
			LayoutInflater vi = (LayoutInflater)mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
			v = vi.inflate( R.layout.listitem_file_browser, null );
		}
		
		ImageView ivType = (ImageView) v.findViewById( R.id.ivType );
		TextView tvFileName = (TextView) v.findViewById( R.id.tvFileName );
		TextView tvLastModified = (TextView) v.findViewById( R.id.tvLastModified );

		if ( mFileBrowserItem.get( position ).isDirectory() ) {
			ivType.setBackgroundResource( R.drawable.directory );
		} else if ( mFileBrowserItem.get( position ).isFile() ) {
			ivType.setBackgroundResource( R.drawable.music );
		} else {
			ivType.setBackgroundDrawable( null );
		}
		
		/*
		 * y 	Year
		 * M 	Month in year
		 * d 	Day in month 
		 * H 	Hour in day (0-23) 
		 * h 	Hour in am/pm (1-12)
		 * m 	Minute in hour 
		 * s 	Second in minute  
		 */
		SimpleDateFormat sdf = new SimpleDateFormat( "yy.MM.dd hh:mm:ss" );
		String lastmodified = sdf.format( new Date( mFileBrowserItem.get( position ).getlLastModified() ) );
		
		tvFileName.setText( mFileBrowserItem.get( position ).getStrFileName() );
		tvLastModified.setText( lastmodified );
		
		return v;
	}

	@Override
	public FileBrowserItem getItem(int position) {
		// TODO Auto-generated method stub
		return super.getItem(position);
	}
}
