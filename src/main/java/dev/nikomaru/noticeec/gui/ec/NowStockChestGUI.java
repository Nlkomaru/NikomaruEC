/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.utils.GetItemMeta;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

@SuppressWarnings( {"ALL","DuplicatedCode"})
public class NowStockChestGUI {
    // 未来へのヒント indexOf()を使用すること
    final SetItemData setItemData = new SetItemData ();

    public Inventory nowPlayerStock (Player p,int pages) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (p,54,makegui.getNowStockChest ());
        int i = 0;
        int num = 45;
        int j = 0;
        while (i < num) {

            int itemNum = (pages - 1) * 45 + i;

            if (StockDataList.getStocks ().size () > itemNum) {
                ArrayList<Object> stock = StockDataList.getStocks ().get (itemNum);


                if (stock.get (1).equals (p.getUniqueId ())) {
                    GetItemMeta getItemMeta = new GetItemMeta ();


                    gui.setItem (i,getItemMeta.setItemMeta (stock));


                    j++;
                }
            } else {
                gui.setItem (i,setItemData.getNoDataGlassItem ());
            }
            i++;
        }
        gui.setItem (45,setItemData.getPrevItem ());
        gui.setItem (46,setItemData.getReloadItem ());
        gui.setItem (47,setItemData.getNextItem ());
        gui.setItem (48,setItemData.getReturnedItem ());
        gui.setItem (49,setItemData.getBuyItem ());
        gui.setItem (50,setItemData.getBuyHistoryItem ());
        gui.setItem (51,setItemData.getSellHistoryItem ());
        gui.setItem (52,setItemData.getTerminalItem ());
        gui.setItem (53,setItemData.getCloseItem ());


        return gui;
    }
}
