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

import java.util.ArrayList;

public class ReturnedChestGUI {
    final SetItemData setItemData = new SetItemData ();

    public Inventory returned (Player p,int pages) {
        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (p,54,makegui.getReturnedChest ());
        int i = 0;
        int num = 45;

        if (!StockDataList.getReturnStocks ().containsKey (p.getUniqueId ())) {
            StockDataList.setReturnPlayerStocks (p.getUniqueId (),ReadReturnStockData.readData (p.getUniqueId ()));
        }
        int stockSize = 0;
        if (StockDataList.getReturnStocks ().get (p.getUniqueId ()) != null) {
            stockSize = StockDataList.getReturnStocks ().get (p.getUniqueId ()).size ();
        }
        while (i < num) {

            if ((pages - 1) * 45 + i < stockSize) {
                GetItemMeta getItemMeta = new GetItemMeta ();

                ArrayList<ArrayList<Object>> stock = StockDataList.getReturnStocks ().get (p.getUniqueId ());
                ArrayList<Object> returned = stock.get ((pages - 1) * 45 + i);
                ArrayList<Object> displayReturned = new ArrayList<> ();

                displayReturned.add (returned.get (0).toString ());
                displayReturned.add (returned.get (1));
                displayReturned.add (returned.get (2));
                displayReturned.add (returned.get (3));
                displayReturned.add (returned.get (4));

                gui.setItem (i,getItemMeta.setReturnItemMeta (displayReturned));
            } else {
                gui.setItem (i,setItemData.getNoDataGlassItem ());
            }
            i++;
        }

        gui.setItem (45,setItemData.getPrevItem ());
        gui.setItem (46,setItemData.getReloadItem ());
        gui.setItem (47,setItemData.getNextItem ());
        gui.setItem (48,setItemData.getBuyItem ());
        gui.setItem (49,setItemData.getStoreItem ());
        gui.setItem (50,setItemData.getPurchaseHistoryItem ());
        gui.setItem (51,setItemData.getSalesHistoryItem ());
        gui.setItem (52,setItemData.getTerminalItem ());
        gui.setItem (53,setItemData.getCloseItem ());

        return gui;
    }
}
