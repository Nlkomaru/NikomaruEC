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

import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetStockItemMeta;
import dev.nikomaru.noticeec.utils.SetTemplateItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class NowStockChestGUI {
    //現在出品中のアイテムを表示するgui
    final SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

    public Inventory nowPlayerStock (Player player,int pages) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (player,54,makegui.getNowStockChest ());
        int i = 0;
        int num = 45;
        while (i < num) {
            int itemNum = (pages - 1) * 45 + i;

            if (StockDataList.getStocks ().size () > itemNum) {

                if (StockDataList.getStocks ().get (itemNum).get (1).equals (player.getUniqueId ())) {
                    SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                    gui.setItem (i,setStockItemMeta.setItemMeta (StockDataList.getStocks ().get (itemNum)));
                }
            } else {
                gui.setItem (i,setTemplateItemData.getNoDataGlassItem ());
            }
            i++;
        }
        gui.setItem (45,setTemplateItemData.getPrevItem ());
        gui.setItem (46,setTemplateItemData.getReloadItem ());
        gui.setItem (47,setTemplateItemData.getNextItem ());
        gui.setItem (48,setTemplateItemData.getReturnedItem ());
        gui.setItem (49,setTemplateItemData.getBuyItem ());
        gui.setItem (50,setTemplateItemData.getPurchaseHistoryItem ());
        gui.setItem (51,setTemplateItemData.getSalesHistoryItem ());
        gui.setItem (52,setTemplateItemData.getTerminalItem ());
        gui.setItem (53,setTemplateItemData.getCloseItem ());

        return gui;
    }
}
