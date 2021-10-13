/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.history;

import com.opencsv.CSVWriter;
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
        String itemName = item.getItemMeta ().getLocalizedName ();

        FileWriter fileWriter;
        CSVWriter csvWriter = null;
        try {
            fileWriter = new FileWriter (buyerUUID.toString () + "_PurchaseHistory.csv");
            csvWriter = new CSVWriter (fileWriter);

            // レコードの作成
            List<String> record = new ArrayList<> ();
            record.add (itemName);
            record.add (sellerUUID.toString ());
            record.add (String.valueOf (price));
            ZonedDateTime nowTime = ZonedDateTime.now ();
            DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm:ss");
            record.add (format.format (nowTime));
            csvWriter.writeNext (record.toArray (new String[record.size ()]));
        } finally {
            if (csvWriter != null) {

                csvWriter.close ();
            }
        }
    }

    public void writeSalesHistory (UUID sellerUUID,ItemStack item,UUID buyerUUID,long price) {
        String itemName = item.getItemMeta ().getLocalizedName ();
    }
}
