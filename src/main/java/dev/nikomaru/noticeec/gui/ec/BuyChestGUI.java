/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.files.returnStocks.ReadReturnStockData;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetStockItemMeta;
import dev.nikomaru.noticeec.utils.SetTemplateItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class BuyChestGUI {
    //購入用のgui
    final SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

    public Inventory Buy (Player player,int pages) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (player,54,makegui.getBuyChest ());
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

                if (!StockDataList.getReturnStocks ().containsKey (player.getUniqueId ())) {
                    StockDataList.setReturnPlayerStocks (player.getUniqueId (),
                            ReadReturnStockData.readData (player.getUniqueId ()));
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

                SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();

                gui.setItem (i,setStockItemMeta.setItemMeta (stock));
            } else {
                gui.setItem (i,setTemplateItemData.getNoDataGlassItem ());
            }
            i++;
        }

        gui.setItem (45,setTemplateItemData.getPrevItem ());
        gui.setItem (46,setTemplateItemData.getReloadItem ());
        gui.setItem (47,setTemplateItemData.getNextItem ());
        gui.setItem (48,setTemplateItemData.getReturnedItem ());
        gui.setItem (49,setTemplateItemData.getStoreItem ());
        gui.setItem (50,setTemplateItemData.getPurchaseHistoryItem ());
        gui.setItem (51,setTemplateItemData.getSalesHistoryItem ());
        gui.setItem (52,setTemplateItemData.getTerminalItem ());
        gui.setItem (53,setTemplateItemData.getCloseItem ());

        return gui;
    }
}

