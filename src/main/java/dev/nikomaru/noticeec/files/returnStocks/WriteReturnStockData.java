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

import dev.nikomaru.noticeec.files.MakeFile;
import dev.nikomaru.noticeec.utils.StockDataList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WriteReturnStockData {
    public void saveData () {

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
