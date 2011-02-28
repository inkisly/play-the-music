package com.inkisly;

public class FileBrowserItem {
	
	private boolean isFile;
	private boolean isDirectory;
	private String strFileName;
	private String strAbsolutePath;
	private long lLastModified;
	
	public FileBrowserItem(boolean isFile, boolean isDirectory,
			String strFileName, String strAbsolutePath, long lLastModified) {
		super();
		this.isFile = isFile;
		this.isDirectory = isDirectory;
		this.strFileName = strFileName;
		this.strAbsolutePath = strAbsolutePath;
		this.lLastModified = lLastModified;
	}
	
	public boolean isFile() {
		return isFile;
	}
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
	
	public boolean isDirectory() {
		return isDirectory;
	}
	
	public void setDirectory(boolean isDirectory) {
		this.isDirectory = isDirectory;
	}
	
	public String getStrFileName() {
		return strFileName;
	}
	
	public void setStrFileName(String strFileName) {
		this.strFileName = strFileName;
	}

	public String getStrAbsolutePath() {
		return strAbsolutePath;
	}

	public void setStrAbsolutePath(String strAbsolutePath) {
		this.strAbsolutePath = strAbsolutePath;
	}

	public long getlLastModified() {
		return lLastModified;
	}

	public void setlLastModified(long lLastModified) {
		this.lLastModified = lLastModified;
	}	
}
