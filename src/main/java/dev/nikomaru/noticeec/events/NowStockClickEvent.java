/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
import dev.nikomaru.noticeec.gui.ec.NowStockChestGUI;
import dev.nikomaru.noticeec.gui.ec.ReturnedChestGUI;
import dev.nikomaru.noticeec.gui.ec.TerminalChestGUI;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.UUID;

public class NowStockClickEvent implements Listener {
    static void changePages (InventoryClickEvent e,Player p,UUID playerUUID,int pages,int i,int maxPage) {

        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;
        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;
        }

        StockDataList.putNowBuyPage (playerUUID,pages + change);
        NowStockChestGUI nowStock = new NowStockChestGUI ();
        p.openInventory (nowStock.nowPlayerStock (p,pages + change));
        e.setCancelled (true);
    }

    //TODO 出品の取り消し
    //TODO 値段の変更
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        MakeGUI makegui = new MakeGUI ();
        if (!(e.getView ().title ().equals (makegui.getNowStockChest ()) && e.getClickedInventory () != null)) {

            return;
        }
        InventoryType inv = e.getClickedInventory ().getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }

        UUID uuid = p.getUniqueId ();
        int pages = StockDataList.getNowStockPage ().get (uuid);
        int clickedSlot = e.getSlot ();
        int num = clickedSlot + (pages - 1) * 45;
        int maxPage = StockDataList.getStocks ().size () / 45;

        //1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる

        switch (clickedSlot) {
            case 45,46,47 -> {
                changePages (e,p,uuid,pages,clickedSlot,maxPage);
            }
            case 48 -> {
                //売れなかった在庫
                ReturnedChestGUI returned = new ReturnedChestGUI ();
                p.openInventory (returned.returned (p,1));
                StockDataList.putReturnPage (p.getUniqueId (),1);
                e.setCancelled (true);
            }
            case 49 -> {
                //物品購入所
                BuyChestGUI buy = new BuyChestGUI ();
                p.openInventory (buy.Buy (p,1));
                StockDataList.putNowBuyPage (p.getUniqueId (),1);
                e.setCancelled (true);
            }
            case 50 -> {
                //購入履歴
            }
            case 51 -> {
                //販売履歴
            }
            case 52 -> {
                //ターミナルを開く
                TerminalChestGUI terminal = new TerminalChestGUI ();
                p.openInventory (terminal.Terminal (p));
            }
            case 53 -> {
                //閉じる
                p.closeInventory ();
            }
            default -> {
            }
        }
        e.setCancelled (true);
    }
}
