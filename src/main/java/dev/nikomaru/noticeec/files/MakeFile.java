package dev.nikomaru.noticeec.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MakeFile {
	public static void makeFile (String dir,String path) {
		
		File directory = new File (dir);
		if (! directory.exists ()) {
			try {
				Files.createDirectory (Path.of (dir));
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}
		
		File file = new File (path);
		if (! file.exists ()) {
			try {
				if (file.createNewFile ()) {
					System.out.println ("ファイルの作成に成功しました。");
				}
				else {
					System.out.println ("ファイルの作成に失敗しました。");
				}
			} catch (IOException e) {
				e.printStackTrace ();
			}
		}
	}
}
