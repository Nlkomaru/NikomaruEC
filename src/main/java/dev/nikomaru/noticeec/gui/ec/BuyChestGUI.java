/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.files.returnStocks.ReadReturnStockData;
import dev.nikomaru.noticeec.utils.GetItemMeta;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class BuyChestGUI {
    //購入用のGUIを作成する処理をする予定
    final SetItemData setItemData = new SetItemData ();

    public Inventory Buy (Player p,int pages) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (p,54,makegui.getBuyChest ());
        int j = 0;

        while (j < StockDataList.getStocks ().size ()) {
            ZonedDateTime nowTime = ZonedDateTime.now ();
            if (nowTime.isAfter ((ZonedDateTime) StockDataList.getStocks ().get (j).get (4))) {
                ArrayList<Object> returnStock = new ArrayList<> ();

                String ReturnStock = StockDataList.getStocks ().get ((pages - 1) * 45 + j).get (0).toString ();
                UUID uuid = (UUID) StockDataList.getStocks ().get ((pages - 1) * 45 + j).get (1);
                Long price = (Long) StockDataList.getStocks ().get ((pages - 1) * 45 + j).get (2);
                String description = StockDataList.getStocks ().get ((pages - 1) * 45 + j).get (3).toString ();
                ZonedDateTime time = ZonedDateTime.now ();

                returnStock.add (ReturnStock);
                returnStock.add (uuid);
                returnStock.add (price);
                returnStock.add (description);
                returnStock.add (time);

                if (!StockDataList.getReturnStocks ().containsKey (p.getUniqueId ())) {
                    StockDataList.setReturnPlayerStocks (p.getUniqueId (),
                            ReadReturnStockData.readData (p.getUniqueId ()));
                }

                StockDataList.addReturnStocks (uuid,returnStock);
                StockDataList.removeStocks ((pages - 1) * 45 + j);
            } else {
                j++;
            }
        }

        int i = 0;
        int num = 45;
        int stockSize = StockDataList.getStocks ().size ();
        ArrayList<Object> stock;

        while (i < num) {

            if ((pages - 1) * 45 + i < stockSize) {
                stock = StockDataList.getStocks ().get ((pages - 1) * 45 + i);

                GetItemMeta getItemMeta = new GetItemMeta ();

                gui.setItem (i,getItemMeta.setItemMeta (stock));
            } else {
                gui.setItem (i,setItemData.getNoDataGlassItem ());
            }
            i++;
        }

        gui.setItem (45,setItemData.getPrevItem ());
        gui.setItem (46,setItemData.getReloadItem ());
        gui.setItem (47,setItemData.getNextItem ());
        gui.setItem (48,setItemData.getReturnedItem ());
        gui.setItem (49,setItemData.getStoreItem ());
        gui.setItem (50,setItemData.getBuyHistoryItem ());
        gui.setItem (51,setItemData.getSellHistoryItem ());
        gui.setItem (52,setItemData.getTerminalItem ());
        gui.setItem (53,setItemData.getCloseItem ());

        return gui;
    }
}

