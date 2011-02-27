package com.inkisly;

import java.io.File;
import java.io.FileFilter;

public class MusicFileFilter implements FileFilter {
	private final String[] okFileExtensions = new String[] { 
			"mp3",
			"ogg",
			"wav" };

	@Override
	public boolean accept(File pathname) {
		for (String extension : okFileExtensions) {
			if (pathname.getName().toLowerCase().endsWith(extension)) {
				return true;
			}
		}
		return false;
	}
}
