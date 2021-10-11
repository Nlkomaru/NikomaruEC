/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
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