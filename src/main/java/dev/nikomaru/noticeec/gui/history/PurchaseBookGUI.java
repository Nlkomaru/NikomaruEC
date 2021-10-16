/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.gui.history;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class PurchaseBookGUI {

    public ItemStack purchaseHistory (Player p) {
        ItemStack book = new ItemStack (Material.WRITTEN_BOOK);
        BookMeta bookMeta = (BookMeta) book.getItemMeta ();
        String path = "plugins\\NoticeEC\\PurchaseHistory\\" + p.getUniqueId () + "_PurchaseHistory.csv";
        File file = new File (path);
        bookMeta.setAuthor ("NoticeEC");
        bookMeta.setTitle ("your purchase history");
        long lines = 0;

        if (!file.exists ()) {
            bookMeta.addPages (Component.text ("あなたはまだ何も購入していません"));
            book.setItemMeta (bookMeta);
            return book;
        }

        try {
            lines = Files.lines (Path.of (path)).count ();
        } catch (IOException e) {
            e.printStackTrace ();
        }

        if (lines <= 0) {
            bookMeta.addPages (Component.text ("あなたはまだ何も購入していません"));
            book.setItemMeta (bookMeta);
            return book;
        }
        Reader fileReader;
        List<String[]> purchaseHistoryList = null;
        try {
            fileReader = new FileReader (path);
            CSVReader csvReader = new CSVReader (fileReader);
            purchaseHistoryList = csvReader.readAll ();
        } catch (IOException | CsvException e) {
            e.printStackTrace ();
        }

        int i = 0;
        while (i < Objects.requireNonNull (purchaseHistoryList).size ()) {
            String[] history = purchaseHistoryList.get (purchaseHistoryList.size () - 1 - i);
            bookMeta.addPages (Component.text ("アイテム名 : " + history[0] + "\n" + "販売者名   : " + (Bukkit.getOfflinePlayer (
                            UUID.fromString (
                                    history[1]))).getName () + "\n" + "金額     : " + history[2] + "\n" + "購入日時   : " + "\n" + history[3],
                    TextColor.color (0,128,0)));
            i++;
        }
        book.setItemMeta (bookMeta);
        return book;
    }
}
