package com.inkisly;

public class AlbumArtItem {
	int nId;
	String strAlbum;
	String strAlbumArt;
	String strArtist;
	
	public AlbumArtItem(int nId, String strAlbum, String strAlbumArt,
			String strArtist) {
		super();
		this.nId = nId;
		this.strAlbum = strAlbum;
		this.strAlbumArt = strAlbumArt;
		this.strArtist = strArtist;
	}

	public int getnId() {
		return nId;
	}
	
	public void setnId(int nId) {
		this.nId = nId;
	}
	
	public String getStrAlbum() {
		return strAlbum;
	}
	
	public void setStrAlbum(String strAlbum) {
		this.strAlbum = strAlbum;
	}
	
	public String getStrAlbumArt() {
		return strAlbumArt;
	}
	
	public void setStrAlbumArt(String strAlbumArt) {
		this.strAlbumArt = strAlbumArt;
	}
	
	public String getStrArtist() {
		return strArtist;
	}
	
	public void setStrArtist(String strArtist) {
		this.strArtist = strArtist;
	}	
}
