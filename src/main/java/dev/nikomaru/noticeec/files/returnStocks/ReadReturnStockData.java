/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.files.returnStocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.UUID;

public class ReadReturnStockData {
    public static ArrayList<ArrayList<Object>> readData (UUID uuid) {
        String path = "plugins\\NoticeEC\\returnedStocks\\" + uuid.toString () + "_returnStock.dat";

        ArrayList<ArrayList<Object>> restoreStocks = new ArrayList<> ();
        File file = new File (path);

        if (!file.exists ()) {
            return null;
        }
        try {

            ObjectInputStream objInStream = new ObjectInputStream (new FileInputStream (path));
            SerializableReturnStock srs = (SerializableReturnStock) objInStream.readObject ();

            objInStream.close ();
            restoreStocks = srs.getStocks ();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ();
        }

        return restoreStocks;
    }
}