package com.inkisly;

import java.io.FileInputStream;
import java.util.ArrayList;

import com.inkisly.util.LogTrace;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;

public class CoverFlowExample extends Activity {
	/** Called when the activity is first created. */

	CoverFlow coverFlow;
	private Context mContext;
	private ImageAdapter mImageAdapter;
	private ArrayList<AlbumArtItem> mAlbumArt = new ArrayList<AlbumArtItem>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Use this if you want to use XML layout file
		setContentView(R.layout.cover_flow);
		mContext = getApplicationContext();
		coverFlow = (CoverFlow) findViewById(R.id.coverflow);

		searchList();
		coverFlow.setAdapter(mImageAdapter);
		coverFlow.setSpacing(-15);
		coverFlow.setSelection(1, true);
		
		coverFlow.setOnItemClickListener( mOnItemClickListener );
		coverFlow.setOnItemSelectedListener( mOnItemSelectedListener );
	}
	
	long selectId = -1;
	long clickId = -1;
	
	CoverAdapterView.OnItemClickListener mOnItemClickListener = new CoverAdapterView.OnItemClickListener() {

		@Override
		public void onItemClick(CoverAdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			LogTrace.d("onItemClick id : " + id );
			clickId = id;
			
			if ( selectId == clickId || 
					selectId == -1 ) {
				Animation ani = AnimationUtils.loadAnimation( mContext, R.anim.slide_from_left );
				view.startAnimation( ani );
			}
		}
	};
	
	CoverAdapterView.OnItemSelectedListener mOnItemSelectedListener = new CoverAdapterView.OnItemSelectedListener() {

		@Override
		public void onItemSelected(CoverAdapterView<?> parent, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			LogTrace.d("onItemSelected id : " + id );
			selectId = id;
		}

		@Override
		public void onNothingSelected(CoverAdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
	};

	public class ImageAdapter extends ArrayAdapter<AlbumArtItem> {

		int mGalleryItemBackground;
		private Context mContext;

		private FileInputStream fis;

		private AlbumArtWrapper albumArt;
		private ArrayList<AlbumArtItem> mAlbumArt;

		public ImageAdapter(Context context, int resource,
				int textViewResourceId, ArrayList<AlbumArtItem> arraylist) {
			super(context, resource, textViewResourceId, arraylist);
			// TODO Auto-generated constructor stub
			mContext = context;
			mAlbumArt = arraylist;
			albumArt = new AlbumArtWrapper(context);
		}

		public Bitmap createReflectedImages(Bitmap originalImage) {
			final int reflectionGap = 4;

			int width = originalImage.getWidth();
			int height = originalImage.getHeight();

			// This will not scale but will flip on the Y axis
			Matrix matrix = new Matrix();
			matrix.preScale(1, -1);

			// Create a Bitmap with the flip matrix applied to it.
			// We only want the bottom half of the image
			Bitmap reflectionImage = Bitmap.createBitmap(originalImage, 0,
					height / 2, width, height / 2, matrix, false);

			// Create a new bitmap with same width but taller to fit
			// reflection
			Bitmap bitmapWithReflection = Bitmap.createBitmap(width,
					(height + height / 2), Config.ARGB_8888);

			// Create a new Canvas with the bitmap that's big enough for
			// the image plus gap plus reflection
			Canvas canvas = new Canvas(bitmapWithReflection);
			// Draw in the original image
			canvas.drawBitmap(originalImage, 0, 0, null);
			// Draw in the gap
			Paint deafaultPaint = new Paint();
			canvas.drawRect(0, height, width, height + reflectionGap,
					deafaultPaint);
			// Draw in the reflection
			canvas.drawBitmap(reflectionImage, 0, height + reflectionGap, null);

			// Create a shader that is a linear gradient that covers the
			// reflection
			Paint paint = new Paint();
			LinearGradient shader = new LinearGradient(0,
					originalImage.getHeight(), 0,
					bitmapWithReflection.getHeight() + reflectionGap,
					0x70ffffff, 0x00ffffff, TileMode.CLAMP);
			// Set the paint to use this shader (linear gradient)
			paint.setShader(shader);
			// Set the Transfer mode to be porter duff and destination in
			paint.setXfermode(new PorterDuffXfermode(Mode.DST_IN));
			// Draw a rectangle using the paint with our linear gradient
			canvas.drawRect(0, height, width, bitmapWithReflection.getHeight()
					+ reflectionGap, paint);

			return bitmapWithReflection;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;

			if (v == null) {
				LayoutInflater vi = (LayoutInflater) mContext
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.listitem_album_browser_grid, null);
			}

			ImageView ivAlbumArt = (ImageView) v.findViewById(R.id.ivAlbumArt);
			TextView tvAlbumTitle = (TextView) v.findViewById(R.id.tvAlbumTitle);
			TextView tvArtist = (TextView) v.findViewById(R.id.tvArtist);
			
			tvAlbumTitle.setText( "asfasf" );

			Bitmap bmp = null;
			
			String strAlbumArt = mAlbumArt.get( position ).getStrAlbumArt();
			if ( strAlbumArt == null || strAlbumArt.length() == 0) {
//				ivAlbumArt.setImageDrawable(null);
				bmp = albumArt.getmDefaultAlbumIcon().getBitmap();
	        } else {
	            long artIndex = mAlbumArt.get( position ).getnId();
	            Drawable d = AlbumArtWrapper.getCachedArtwork(mContext, artIndex, albumArt.getmDefaultAlbumIcon());
//	            ivAlbumArt.setImageDrawable(d);
	            int w = albumArt.getmDefaultAlbumIcon().getBitmap().getWidth();
	            int h = albumArt.getmDefaultAlbumIcon().getBitmap().getHeight();
	            bmp = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888 );
	            Canvas canvas = new Canvas(bmp);
	            d.setBounds(0, 0, w, h);
	            d.draw(canvas);	            
	        }

			ivAlbumArt.setBackgroundDrawable(null);
			ivAlbumArt.setImageBitmap( createReflectedImages(bmp) );
			ivAlbumArt.setLayoutParams(new CoverFlow.LayoutParams(250, 350));
			ivAlbumArt.setScaleType(ScaleType.MATRIX);
			
			return ivAlbumArt;
		}

		/**
		 * Returns the size (0.0f to 1.0f) of the views depending on the
		 * 'offset' to the center.
		 */
		public float getScale(boolean focused, int offset) {
			/* Formula: 1 / (2 ^ offset) */
			return Math.max(0, 1.0f / (float) Math.pow(2, Math.abs(offset)));
		}
	}

	private void searchList() {
		ContentsDBManager cm = new ContentsDBManager(mContext);

		Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
		String[] projection = new String[] { MediaStore.Audio.Albums._ID,
				MediaStore.Audio.Albums.ARTIST, MediaStore.Audio.Albums.ALBUM,
				MediaStore.Audio.Albums.ALBUM_ART };
		String selection = null;
		String[] selectionArgs = null;
		String sortOrder = null;

		Cursor c = cm.query(uri, projection, selection, selectionArgs,
				sortOrder);
		
		if ( c != null && c.getCount() > 0 ) {
			c.moveToFirst();
			do {
				int nId = c.getInt( c.getColumnIndex( MediaStore.Audio.Albums._ID ) );
				String strAlbumArt = c.getString( c.getColumnIndex( MediaStore.Audio.Albums.ALBUM_ART ) );
				String strAlbum = c.getString( c.getColumnIndex( MediaStore.Audio.Albums.ALBUM ) );
				String strArtist = c.getString( c.getColumnIndex( MediaStore.Audio.Albums.ARTIST ) );
				
				AlbumArtItem item = new AlbumArtItem(nId, strAlbum, strAlbumArt, strArtist);
				mAlbumArt.add(item);
				
			} while (c.moveToNext());
			
			c.close();
		}
		
		mImageAdapter = new ImageAdapter(mContext, R.layout.listitem_album_browser_grid, 0, mAlbumArt );
	}
}
