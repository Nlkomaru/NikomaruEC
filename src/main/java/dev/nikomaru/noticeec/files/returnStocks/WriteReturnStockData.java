/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.returnStocks;

import dev.nikomaru.noticeec.files.MakeFile;
import dev.nikomaru.noticeec.utils.StockDataList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class WriteReturnStockData {
    public static void saveData () {
        String dir = "plugins\\NoticeEC\\returnStock";
        String path = dir + "\\returnStock.dat";


        if (StockDataList.getReturnStocks ().isEmpty ()) {
            return;
        }
        MakeFile.makeFile (dir,path);
        HashMap<UUID,List<List<Object>>> returnStock = StockDataList.getReturnStocks ();
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
    }
}
