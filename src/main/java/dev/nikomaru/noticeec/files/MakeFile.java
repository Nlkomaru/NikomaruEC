/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MakeFile {
    public static void makeFile (String dir,String path) {

        File directory = new File (dir);
        if (!directory.exists ()) {
            try {
                Files.createDirectory (Path.of (dir));
            } catch (IOException e) {
                e.printStackTrace ();
            }
        }

        File file = new File (path);
        if (file.exists ()) {
            return;
        }
        try {
            String str;
            str = file.createNewFile () ? "ファイルの作成に成功しました。" : "ファイルの作成に失敗しました。";
            System.out.println (str);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }
}
