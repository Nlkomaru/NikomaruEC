/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
import dev.nikomaru.noticeec.gui.ec.SellChestGUI;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class TerminalClickEvent implements Listener {
    //いろいろなところにアクセスできるGUIのクリックを処理する予定
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked ();
        MakeGUI makegui = new MakeGUI ();

        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getTerminalChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = e.getClickedInventory ().getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }

        switch (e.getSlot ()) {
            case 0 -> {
                //物品購入所
                BuyChestGUI buy = new BuyChestGUI ();
                player.openInventory (buy.Buy (player,1));
                StockDataList.getNowBuyPage ().put (player.getUniqueId (),1);
            }
            case 1 -> {
                //物品販売所
                SellChestGUI sell = new SellChestGUI ();
                player.openInventory (sell.Sell (player));
                e.getClickedInventory ().close ();
            }
            case 8 -> {
                //閉じる
                player.closeInventory ();
                e.getClickedInventory ().close ();
            }
        }
        e.setCancelled (true);
    }
}


