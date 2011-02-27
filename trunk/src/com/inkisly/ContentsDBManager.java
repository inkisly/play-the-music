package com.inkisly;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

public class ContentsDBManager
{
	ContentResolver mCR = null;

	public ContentsDBManager( Context context )
	{
		mCR = context.getContentResolver();
	}

	public ContentResolver getContentResolver()
	{
		return mCR;
	}

	public Cursor query( Uri uri, String[] projection,
			String selection, String[] selectionArgs, String sortOrder )
	{
		return mCR.query( uri, projection, selection, selectionArgs, sortOrder );
	}

	public Cursor getMyList( String playlistName )
	{
		Uri ContentUri = MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI;
		String strSelection = null;

		String[] mPlaylistCols = new String[] { MediaStore.Audio.Playlists._ID,
				MediaStore.Audio.Playlists.NAME,
				MediaStore.Audio.Playlists.DATA,
				MediaStore.Audio.Playlists.DATE_ADDED,
				MediaStore.Audio.Playlists.DATE_MODIFIED };

		if ( playlistName != null )
		{
			strSelection = MediaStore.Audio.Playlists.NAME + " = "
					+ playlistName;
		}

		return mCR.query( ContentUri, mPlaylistCols, strSelection, null,
				MediaStore.Audio.Playlists.NAME );
	}

	public int deleteMyList( int mylistId )
	{
		Uri ContentUri = Uri.withAppendedPath(
				MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
				Integer.toString( mylistId ) );
		String strSelection = null;

		return mCR.delete( ContentUri, null, null );
	}

	public Cursor getMyListDetail( int mylistId )
	{
		return getMyListDetail( mylistId, null );
	}

	public Cursor getMyListDetail( int mylistId, String[] cols )
	{
		Uri ContentUri = MediaStore.Audio.Playlists.Members.getContentUri(
				"external", mylistId );
		String strSelection = null;

		if ( cols == null )
		{
			String[] mPlaylistMemberCols = new String[] {
					MediaStore.Audio.Playlists.Members._ID,
					MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
					MediaStore.Audio.Media.ALBUM,
					MediaStore.Audio.Media.ALBUM_ID,
					MediaStore.Audio.Media.ARTIST,
					MediaStore.Audio.Media.ARTIST_ID,
					MediaStore.Audio.Media.DURATION,
					MediaStore.Audio.Playlists.Members.PLAY_ORDER,
					MediaStore.Audio.Playlists.Members.AUDIO_ID,
					MediaStore.Audio.Media.IS_MUSIC };

			cols = mPlaylistMemberCols;
		}

		return mCR.query( ContentUri, cols, strSelection, null, null );
	}

	public Cursor getMyListDetailCount( int mylistId )
	{
		Uri ContentUri = MediaStore.Audio.Playlists.Members.getContentUri(
				"external", mylistId );
		String strSelection = null;

		return mCR.query( ContentUri, new String[] { "count(*)",
				MediaStore.Audio.Playlists._ID }, strSelection, null, null );
	}

	public Cursor getMyListDetailPrimity( int mylistId )
	{
		Uri ContentUri = MediaStore.Audio.Playlists.Members.getContentUri("external", mylistId );
		
		String strSelection = null;

		String[] mPlaylistMemberCols = new String[] {
				MediaStore.Audio.Playlists.Members.PLAYLIST_ID,
				MediaStore.Audio.Media.TITLE, MediaStore.Audio.Media.DATA,
				MediaStore.Audio.Media.ALBUM,
				MediaStore.Audio.Playlists.Members.AUDIO_ID,
				MediaStore.Audio.Playlists.Members.PLAY_ORDER, };

		ContentUri = ContentUri.buildUpon().appendQueryParameter( "limit", "1" ).build();

		return mCR.query( ContentUri, mPlaylistMemberCols, strSelection, null,
				MediaStore.Audio.Playlists.Members.PLAY_ORDER );
	}

	public int updateMyListThumbnail( int mylistId, String _thumbnail )
	{
		Uri ContentUri = Uri.withAppendedPath(
				MediaStore.Audio.Playlists.EXTERNAL_CONTENT_URI,
				Integer.toString( mylistId ) );

		ContentValues values = new ContentValues();
		values.put( MediaStore.Audio.Playlists.DATA, _thumbnail );

		return mCR.update( ContentUri, values, null, null );
	}
}
