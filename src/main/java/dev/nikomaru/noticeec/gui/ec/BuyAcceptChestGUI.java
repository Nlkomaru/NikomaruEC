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
