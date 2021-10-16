/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetStockItemMeta;
import dev.nikomaru.noticeec.utils.SetTemplateItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class BuyAcceptChestGUI {
    //購入を承認するgui
    public Inventory BuyAccept (Player player,int i) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (player,9,makegui.getBuyAcceptChest ());
        StockDataList.putSelectNum (player.getUniqueId (),i);

        SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();
        SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();

        ArrayList<Object> stock = StockDataList.getStocks ().get (i);

        gui.setItem (0,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (1,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (2,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (3,setStockItemMeta.setItemMeta (stock));
        gui.setItem (4,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (5,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (6,setTemplateItemData.getBlankGlassItem ());
        gui.setItem (7,setTemplateItemData.getAcceptItem ());
        gui.setItem (8,setTemplateItemData.getDenyItem ());

        return gui;
    }
}
