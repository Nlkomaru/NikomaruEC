/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
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
    static void changePages (InventoryClickEvent e,Player player,UUID playerUUID,int pages,int i,int maxPage) {

        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;
        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;
        }

        StockDataList.putReturnPage (playerUUID,pages + change);
        ReturnedChestGUI returned = new ReturnedChestGUI ();
        player.openInventory (returned.returned (player,pages + change));
        e.setCancelled (true);
    }

    @EventHandler
    public void clickEvent (InventoryClickEvent e) {
        MakeGUI makegui = new MakeGUI ();

        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getReturnedChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = e.getClickedInventory ().getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }
        Player player = (Player) e.getWhoClicked ();
        UUID uuid = player.getUniqueId ();

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
                changePages (e,player,uuid,pages,clickedSlot,maxPage);
                e.setCancelled (true);
            }
            case 48 -> {
                //販売場
                BuyChestGUI buyChestGUI = new BuyChestGUI ();
                player.openInventory (buyChestGUI.Buy (player,1));
                StockDataList.putNowBuyPage (uuid,1);
            }
            case 49 -> {
                //自分の販売中の在庫
                NowStockChestGUI nowStock = new NowStockChestGUI ();
                player.openInventory (nowStock.nowPlayerStock (player,1));
                StockDataList.putNowStockPage (player.getUniqueId (),1);
            }
            case 50 -> {
                //購入履歴
                PurchaseBookGUI purchaseBookGUI = new PurchaseBookGUI ();
                player.openBook (purchaseBookGUI.purchaseHistory (player));
            }
            case 51 -> {
                //販売履歴
                SalesBookGUI salesBookGUI = new SalesBookGUI ();
                player.openBook (salesBookGUI.salesHistory (player));
            }
            case 52 -> {
                //ターミナルを開く
                TerminalChestGUI terminal = new TerminalChestGUI ();
                player.openInventory (terminal.Terminal (player));
            }
            case 53 -> {
                //閉じる
                player.closeInventory ();
            }
            default -> {
                if (num >= returnedNum) {
                    e.setCancelled (true);
                    return;
                }
                if (player.getInventory ().firstEmpty () != -1) {
                    player.getInventory ().addItem (ChangeItemData.decode (
                            StockDataList.getReturnStocks ().get (uuid).get (num).get (0).toString ()));
                    StockDataList.removeReturnStocks (uuid,num);
                    ReturnedChestGUI returnedChestGUI = new ReturnedChestGUI ();
                    player.openInventory (returnedChestGUI.returned (player,pages));
                    e.setCancelled (true);
                    return;
                }
                SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();
                e.getClickedInventory ().setItem (clickedSlot,setTemplateItemData.getNoticeNoEmptyItem ());
                new BukkitRunnable () {
                    @Override
                    public void run () {
                        SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                        e.getClickedInventory ().setItem (clickedSlot,
                                setStockItemMeta.setItemMeta (StockDataList.getReturnStocks ().get (uuid).get (num)));
                    }
                }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                e.setCancelled (true);
            }
        }
        e.setCancelled (true);
    }
}
