/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.stocks;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class ReadStockData {
    //アイテムのデータを取得する処理をする予定
    public static List<List<Object>> readData () {

        // {itemStack} {player uuid} {price} {description} {time}


        String path = "plugins\\NoticeEC\\stock.dat";

        List<List<Object>> storeStocks = new ArrayList<> ();
        List<List<Object>> restoreStocks = new ArrayList<> ();

        File file = new File (path);
        if (!file.exists ()) {
            return restoreStocks;
        }
        try {
            ObjectInputStream objInStream = new ObjectInputStream (new FileInputStream (path));
            SerializableStock ss = (SerializableStock) objInStream.readObject ();

            objInStream.close ();
            storeStocks = ss.getStocks ();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        // {itemStack} {player uuid} {price} {description} {time}

        for (List<Object> objects : storeStocks) {
            List<Object> stock = new ArrayList<> ();

            String item = objects.get (0).toString ();
            UUID uuid = (UUID) objects.get (1);
            Long price = (Long) objects.get (2);
            String description = objects.get (3).toString ();
            ZonedDateTime time = (ZonedDateTime) objects.get (4);


            stock.add (item);
            stock.add (uuid);
            stock.add (price);
            stock.add (description);
            stock.add (time);

            restoreStocks.add (stock);
        }

        return restoreStocks;
    }
}
