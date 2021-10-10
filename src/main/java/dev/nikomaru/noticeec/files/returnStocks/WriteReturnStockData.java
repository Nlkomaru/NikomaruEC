/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.returnStocks;

import dev.nikomaru.noticeec.files.MakeFile;
import dev.nikomaru.noticeec.utils.StockDataList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WriteReturnStockData {
    public static void saveData () {

        if (StockDataList.getReturnStocks ().isEmpty ()) {
            return;
        }
        List<UUID> keyList = new ArrayList<> (StockDataList.getReturnStocks ().keySet ());

        while (!keyList.isEmpty ()) {

            UUID uuid = keyList.get (0);
            String dir = "plugins\\NoticeEC\\returnedStocks\\";
            String path = dir + uuid.toString () + "_returnStock.dat";

            ArrayList<ArrayList<Object>> returnStock = StockDataList.getReturnStocks ().get (uuid);

            MakeFile.makeFile (dir,path);
            try {
                ObjectOutputStream objOutStream = new ObjectOutputStream (new FileOutputStream (path));
                SerializableReturnStock srs = new SerializableReturnStock (returnStock);

                objOutStream.writeObject (srs);
                objOutStream.flush ();
                objOutStream.reset ();
                objOutStream.close ();
            } catch (IOException e) {
                e.printStackTrace ();
            }
            keyList.remove (0);
        }
    }
}
