/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
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
