package com.inkisly;

import android.content.Context;
import android.database.Cursor;
import android.database.CursorWrapper;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleCursorTreeAdapter;
import android.widget.TextView;

public class ArtistBrowserAdapter extends SimpleCursorTreeAdapter {

	private Context mContext;
	private AlbumArtWrapper albumArt;

	public ArtistBrowserAdapter(Context context, Cursor cursor,
			int groupLayout, String[] groupFrom, int[] groupTo,
			int childLayout, String[] childFrom, int[] childTo) {
		super(context, cursor, groupLayout, groupFrom, groupTo, childLayout,
				childFrom, childTo);
		// TODO Auto-generated constructor stub
		mContext = context;
		albumArt = new AlbumArtWrapper( context );
	}

	static class ViewHolder {
		TextView tvArtist;
		TextView tvAlbumCount;

		ImageView ivAlbumArt;
		TextView tvAlbumTitle;
		TextView tvTrackCount;
	}

	@Override
	protected Cursor getChildrenCursor(Cursor groupCursor) {
		// TODO Auto-generated method stub
		
		if ( mContext == null ) return null;
		
		int groupId = groupCursor.getInt( groupCursor.getColumnIndex( MediaStore.Audio.Artists._ID ) );
		
		ContentsDBManager cm = new ContentsDBManager( mContext );
		
		Uri uri = MediaStore.Audio.Artists.Albums.getContentUri( "external", groupId );
		String [] projection = new String[] {
                MediaStore.Audio.Albums._ID,
                MediaStore.Audio.Albums.ALBUM,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS,
                MediaStore.Audio.Albums.NUMBER_OF_SONGS_FOR_ARTIST,
                MediaStore.Audio.Albums.ALBUM_ART
        };;
		String selection = null;
		String [] selectionArgs = null;
		String sortOrder = null;		

		Cursor c = cm.query(uri, projection, selection, selectionArgs, sortOrder);

        class MyCursorWrapper extends CursorWrapper {
            String mArtistName;
            int mMagicColumnIdx;
            MyCursorWrapper(Cursor c, String artist) {
                super(c);
                mArtistName = artist;
                if (mArtistName == null || mArtistName.equals(MediaStore.UNKNOWN_STRING)) {
//                    mArtistName = mUnknownArtist;
                }
                mMagicColumnIdx = c.getColumnCount();
            }
            
            @Override
            public String getString(int columnIndex) {
                if (columnIndex != mMagicColumnIdx) {
                    return super.getString(columnIndex);
                }
                return mArtistName;
            }
            
            @Override
            public int getColumnIndexOrThrow(String name) {
                if (MediaStore.Audio.Albums.ARTIST.equals(name)) {
                    return mMagicColumnIdx;
                }
                return super.getColumnIndexOrThrow(name); 
            }
            
            @Override
            public String getColumnName(int idx) {
                if (idx != mMagicColumnIdx) {
                    return super.getColumnName(idx);
                }
                return MediaStore.Audio.Albums.ARTIST;
            }
            
            @Override
            public int getColumnCount() {
                return super.getColumnCount() + 1;
            }
        }
        
        return new MyCursorWrapper(c, groupCursor.getString(groupCursor.getColumnIndex( MediaStore.Audio.Artists.ARTIST )));
	}

	@Override
	protected void bindChildView(View view, Context context, Cursor cursor,
			boolean isLastChild) {
		// TODO Auto-generated method stub
		ViewHolder vh = (ViewHolder) view.getTag();

		String album = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Albums.ALBUM ) );
		String trackcount = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS ) );
		String albumart = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART ) );

		vh.tvAlbumTitle.setText(album);
		vh.tvTrackCount.setText(trackcount);
		
		if ( albumart == null || albumart.length() == 0) {
			vh.ivAlbumArt.setBackgroundDrawable( albumArt.getmDefaultAlbumIcon());
			vh.ivAlbumArt.setImageDrawable(null);
        } else {
            long artIndex = cursor.getLong( cursor.getColumnIndex( MediaStore.Audio.Albums._ID ) );
            Drawable d = AlbumArtWrapper.getCachedArtwork(context, artIndex, albumArt.getmDefaultAlbumIcon());
            vh.ivAlbumArt.setImageDrawable(d);
        }
		
		super.bindChildView(view, context, cursor, isLastChild);
	}

	@Override
	protected void bindGroupView(View view, Context context, Cursor cursor,
			boolean isExpanded) {
		// TODO Auto-generated method stub
		ViewHolder vh = (ViewHolder) view.getTag();

		String artist = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Artists.ARTIST ));
		String albumCount = cursor.getString(cursor
				.getColumnIndex(MediaStore.Audio.Artists.NUMBER_OF_ALBUMS ));

		vh.tvArtist.setText(artist);
		vh.tvAlbumCount.setText(albumCount);

		super.bindGroupView(view, context, cursor, isExpanded);
	}

	@Override
	public View newChildView(Context context, Cursor cursor,
			boolean isLastChild, ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.newChildView(context, cursor, isLastChild, parent);
		View v = super.newChildView(context, cursor, isLastChild, parent);
		ViewHolder vh = new ViewHolder();
		
		vh.ivAlbumArt = (ImageView) v.findViewById( R.id.ivAlbumArt );
		vh.tvAlbumTitle = (TextView) v.findViewById(R.id.tvAlbumTitle);
		vh.tvTrackCount = (TextView) v.findViewById(R.id.tvTrackCount);
		
		vh.ivAlbumArt.setBackgroundDrawable( albumArt.getmDefaultAlbumIcon());

		v.setTag(vh);
		return v;
	}

	@Override
	public View newGroupView(Context context, Cursor cursor,
			boolean isExpanded, ViewGroup parent) {
		// TODO Auto-generated method stub
		// return super.newGroupView(context, cursor, isExpanded, parent);
		View v = super.newGroupView(context, cursor, isExpanded, parent);
		ViewHolder vh = new ViewHolder();

		vh.tvArtist = (TextView) v.findViewById(R.id.tvArtist);
		vh.tvAlbumCount = (TextView) v.findViewById(R.id.tvAlbumCount);

		v.setTag(vh);
		return v;
	};
}
