/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
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

import java.util.ArrayList;

public class ReturnedChestGUI {
    //期限が切れて返却されたアイテムのgui
    final SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

    public Inventory returned (Player player,int pages) {
        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (player,54,makegui.getReturnedChest ());
        int i = 0;
        int num = 45;

        if (!StockDataList.getReturnStocks ().containsKey (player.getUniqueId ())) {
            StockDataList.setReturnPlayerStocks (player.getUniqueId (),
                    ReadReturnStockData.readData (player.getUniqueId ()));
        }
        int stockSize = 0;
        if (StockDataList.getReturnStocks ().get (player.getUniqueId ()) != null) {
            stockSize = StockDataList.getReturnStocks ().get (player.getUniqueId ()).size ();
        }
        while (i < num) {

            if ((pages - 1) * 45 + i < stockSize) {
                SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();

                ArrayList<ArrayList<Object>> stock = StockDataList.getReturnStocks ().get (player.getUniqueId ());
                ArrayList<Object> returned = stock.get ((pages - 1) * 45 + i);
                ArrayList<Object> displayReturned = new ArrayList<> ();

                displayReturned.add (returned.get (0).toString ());
                displayReturned.add (returned.get (1));
                displayReturned.add (returned.get (2));
                displayReturned.add (returned.get (3));
                displayReturned.add (returned.get (4));

                gui.setItem (i,setStockItemMeta.setReturnItemMeta (displayReturned));
            } else {
                gui.setItem (i,setTemplateItemData.getNoDataGlassItem ());
            }
            i++;
        }

        gui.setItem (45,setTemplateItemData.getPrevItem ());
        gui.setItem (46,setTemplateItemData.getReloadItem ());
        gui.setItem (47,setTemplateItemData.getNextItem ());
        gui.setItem (48,setTemplateItemData.getBuyItem ());
        gui.setItem (49,setTemplateItemData.getStoreItem ());
        gui.setItem (50,setTemplateItemData.getPurchaseHistoryItem ());
        gui.setItem (51,setTemplateItemData.getSalesHistoryItem ());
        gui.setItem (52,setTemplateItemData.getTerminalItem ());
        gui.setItem (53,setTemplateItemData.getCloseItem ());

        return gui;
    }
}
