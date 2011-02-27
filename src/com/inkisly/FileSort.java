package com.inkisly;

import java.io.File;
import java.util.Comparator;

public class FileSort {
	class ModifiedDateSort implements Comparator<File> {

		public int compare(File f1, File f2) {

//			������¥�� ���� ��ũ�� -1����, -1�� �����ϸ� ù��°���� ������ ����.
			if (f1.lastModified() > f2.lastModified())
				return 1;
//
//			������ 0
			if (f1.lastModified() == f2.lastModified())
				return 0;

//			������ 1
			return -1;
		}
	}

	class FileNameSort implements Comparator<File> {

		public int compare(File f1, File f2) {
			
			return f1.getName().toLowerCase().compareTo( f2.getName().toLowerCase() );
		}
	}
	
	class FileDirectorySort implements Comparator<File> {

		public int compare(File f1, File f2) {
			
			if ( ( f1.isDirectory() && !f2.isFile() ) || 
					( f1.isFile() && f2.isDirectory() )) {
				return 1;
			}
			else if ( ( f1.isFile() && f2.isFile() )  ) {
				return 0;
			}
			else {
				return -1;
			}
		}
	}
}
