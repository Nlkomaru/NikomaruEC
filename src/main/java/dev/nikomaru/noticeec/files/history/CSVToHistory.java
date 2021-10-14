/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.history;

import com.opencsv.CSVWriter;
import dev.nikomaru.noticeec.files.MakeFile;
import org.bukkit.inventory.ItemStack;

import java.io.FileWriter;
import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/*
 * アイテム名
 * 販売者または購入者
 * 金額
 * 購入日時
 */

public class CSVToHistory {
    //購入履歴
    public void writePurchaseHistory (UUID buyerUUID,ItemStack item,UUID sellerUUID,long price) throws IOException {
        String itemName = item.getI18NDisplayName ();

        FileWriter fileWriter;
        CSVWriter csvWriter = null;
        String dir = "plugins\\NoticeEC\\PurchaseHistory\\";
        String path = dir + buyerUUID.toString () + "_PurchaseHistory.csv";
        MakeFile.makeFile (dir,path);
        try {
            fileWriter = new FileWriter (path,true);
            csvWriter = new CSVWriter (fileWriter);

            // レコードの作成
            List<String> record = new ArrayList<> ();
            record.add (itemName);
            record.add (sellerUUID.toString ());
            record.add (String.valueOf (price));
            ZonedDateTime nowTime = ZonedDateTime.now ();
            DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm:ss");
            record.add (format.format (nowTime));
            csvWriter.writeNext (record.toArray (new String[0]));
        } finally {
            if (csvWriter != null) {

                csvWriter.close ();
            }
        }
    }

    public void writeSalesHistory (UUID sellerUUID,ItemStack item,UUID buyerUUID,long price) throws IOException {
        String itemName = item.getI18NDisplayName ();

        FileWriter fileWriter;
        CSVWriter csvWriter = null;
        String dir = "plugins\\NoticeEC\\SalesHistory\\";
        String path = dir + sellerUUID.toString () + "_SalesHistory.csv";
        MakeFile.makeFile (dir,path);
        try {
            fileWriter = new FileWriter (path,true);
            csvWriter = new CSVWriter (fileWriter);

            // レコードの作成
            List<String> record = new ArrayList<> ();
            record.add (itemName);
            record.add (buyerUUID.toString ());
            record.add (String.valueOf (price));
            ZonedDateTime nowTime = ZonedDateTime.now ();
            DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm:ss");
            record.add (format.format (nowTime));
            csvWriter.writeNext (record.toArray (new String[0]));
            csvWriter.flush ();
        } finally {
            if (csvWriter != null) {

                csvWriter.close ();
            }
        }
    }
}
