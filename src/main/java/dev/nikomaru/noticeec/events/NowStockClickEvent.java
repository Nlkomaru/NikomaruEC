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

import java.util.HashMap;
import java.util.UUID;

public class NowStockClickEvent implements Listener {
    static void changePages (InventoryClickEvent e,Player player,UUID playerUUID,int pages,int i,int maxPage) {

        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;
        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;
        }

        StockDataList.putNowBuyPage (playerUUID,pages + change);
        NowStockChestGUI nowStock = new NowStockChestGUI ();
        player.openInventory (nowStock.nowPlayerStock (player,pages + change));
        e.setCancelled (true);
    }

    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked ();
        MakeGUI makegui = new MakeGUI ();
        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getNowStockChest ()) && e.getClickedInventory () != null)) {

            return;
        }
        InventoryType inv = e.getClickedInventory ().getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }

        UUID uuid = player.getUniqueId ();
        int pages = StockDataList.getNowStockPage ().get (uuid);
        int clickedSlot = e.getSlot ();
        int num = clickedSlot + (pages - 1) * 45;
        int maxPage = (int) Math.ceil ((double) StockDataList.getStocks ().size () / 45);

        switch (clickedSlot) {
            case 45,46,47 -> {
                changePages (e,player,uuid,pages,clickedSlot,maxPage);
            }
            case 48 -> {
                //売れなかった在庫
                ReturnedChestGUI returned = new ReturnedChestGUI ();
                player.openInventory (returned.returned (player,1));
                StockDataList.putReturnPage (player.getUniqueId (),1);
            }
            case 49 -> {
                //物品購入所
                BuyChestGUI buy = new BuyChestGUI ();
                player.openInventory (buy.Buy (player,1));
                StockDataList.putNowBuyPage (player.getUniqueId (),1);
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
                HashMap<Integer,Integer> itemIndex = new HashMap<> ();
                int i = 0;
                int j = 0;
                while (i < 45) {
                    int itemNum = (pages - 1) * 45 + i;
                    if (StockDataList.getStocks ().size () > itemNum) {
                        if (StockDataList.getStocks ().get (i).get (1).equals (player.getUniqueId ())) {
                            itemIndex.put (j,i);
                            j++;
                        }
                    }
                    i++;
                }
                if (num >= itemIndex.size ()) {
                    e.setCancelled (true);
                    return;
                }

                if (player.getInventory ().firstEmpty () == -1) {
                    SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();
                    e.getClickedInventory ().setItem (clickedSlot,setTemplateItemData.getNoticeNoEmptyItem ());

                    new BukkitRunnable () {
                        @Override
                        public void run () {
                            SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                            e.getClickedInventory ().setItem (clickedSlot,
                                    setStockItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                        }
                    }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                    e.setCancelled (true);
                    return;
                }

                player.getInventory ().addItem (ChangeItemData.decode (
                        StockDataList.getStocks ().get (itemIndex.get (num)).get (0).toString ()));
                StockDataList.removeStocks (itemIndex.get (num));

                NowStockChestGUI nowStockChestGUI = new NowStockChestGUI ();
                player.openInventory (nowStockChestGUI.nowPlayerStock (player,Math.min (pages,maxPage)));
                e.setCancelled (true);
            }
        }
    }
}
