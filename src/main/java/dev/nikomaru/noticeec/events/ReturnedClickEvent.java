/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
import dev.nikomaru.noticeec.gui.ec.NowStockChestGUI;
import dev.nikomaru.noticeec.gui.ec.ReturnedChestGUI;
import dev.nikomaru.noticeec.gui.ec.TerminalChestGUI;
import dev.nikomaru.noticeec.gui.history.PurchaseBookGUI;
import dev.nikomaru.noticeec.gui.history.SalesBookGUI;
import dev.nikomaru.noticeec.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class ReturnedClickEvent implements Listener {
    static void changePages (InventoryClickEvent e,Player p,UUID playerUUID,int pages,int i,int maxPage) {

        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;
        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;
        }

        StockDataList.putReturnPage (playerUUID,pages + change);
        ReturnedChestGUI returned = new ReturnedChestGUI ();
        p.openInventory (returned.returned (p,pages + change));
        e.setCancelled (true);
    }

    @EventHandler
    public void clickEvent (InventoryClickEvent e) {
        MakeGUI makegui = new MakeGUI ();
        if (!(e.getView ().title ().equals (makegui.getReturnedChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = e.getClickedInventory ().getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }
        Player p = (Player) e.getWhoClicked ();
        UUID uuid = p.getUniqueId ();

        int clickedSlot = e.getSlot ();
        int pages = StockDataList.getReturnPage ().get (uuid);
        int returnedNum = 0;
        if (StockDataList.getReturnStocks ().get (uuid) != null) {
            returnedNum = StockDataList.getReturnStocks ().get (uuid).size ();
        }

        int maxPage = (int) Math.ceil ((double) returnedNum / 45);
        int num = clickedSlot + (pages - 1) * 45;

        switch (clickedSlot) {
            case 45,46,47 -> {
                changePages (e,p,uuid,pages,clickedSlot,maxPage);
                e.setCancelled (true);
            }
            case 48 -> {
                //販売場
                BuyChestGUI buyChestGUI = new BuyChestGUI ();
                p.openInventory (buyChestGUI.Buy (p,1));
                StockDataList.putNowBuyPage (uuid,1);
            }
            case 49 -> {
                //自分の販売中の在庫
                NowStockChestGUI nowStock = new NowStockChestGUI ();
                p.openInventory (nowStock.nowPlayerStock (p,1));
                StockDataList.putNowStockPage (p.getUniqueId (),1);
            }
            case 50 -> {
                //購入履歴
                PurchaseBookGUI purchaseBookGUI = new PurchaseBookGUI ();
                p.openBook (purchaseBookGUI.purchaseHistory (p));
            }
            case 51 -> {
                //販売履歴
                SalesBookGUI salesBookGUI = new SalesBookGUI ();
                p.openBook (salesBookGUI.salesHistory (p));
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
                if (num >= returnedNum) {
                    e.setCancelled (true);
                    return;
                }
                if (p.getInventory ().firstEmpty () != -1) {
                    p.getInventory ().addItem (ChangeItemData.decode (
                            StockDataList.getReturnStocks ().get (uuid).get (num).get (0).toString ()));
                    StockDataList.removeReturnStocks (uuid,num);
                    ReturnedChestGUI returnedChestGUI = new ReturnedChestGUI ();
                    p.openInventory (returnedChestGUI.returned (p,pages));
                    e.setCancelled (true);
                    return;
                }
                SetItemData setItemData = new SetItemData ();
                e.getClickedInventory ().setItem (clickedSlot,setItemData.getNoticeNoEmptyItem ());
                new BukkitRunnable () {
                    @Override
                    public void run () {
                        GetItemMeta getItemMeta = new GetItemMeta ();
                        e.getClickedInventory ().setItem (clickedSlot,
                                getItemMeta.setItemMeta (StockDataList.getReturnStocks ().get (uuid).get (num)));
                    }
                }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                e.setCancelled (true);
            }
        }
    }
}
