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
import dev.nikomaru.noticeec.api.VaultAPI;
import dev.nikomaru.noticeec.gui.ec.*;
import dev.nikomaru.noticeec.gui.history.PurchaseBookGUI;
import dev.nikomaru.noticeec.gui.history.SalesBookGUI;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetStockItemMeta;
import dev.nikomaru.noticeec.utils.SetTemplateItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;
import java.util.UUID;

public class BuyClickEvent implements Listener {
    static void changePages (InventoryClickEvent e,Player player,UUID playerUUID,int pages,int i,int maxPage) {
        //ページの変更
        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;
        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;
        }

        StockDataList.putNowBuyPage (playerUUID,pages + change);
        BuyChestGUI buy = new BuyChestGUI ();
        player.openInventory (buy.Buy (player,pages + change));
        e.setCancelled (true);
    }

    //購入用のアイテムがクリックされたら購入用GUIに飛ぶ処理をする予定
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        MakeGUI makegui = new MakeGUI ();
        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getBuyChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = Objects.requireNonNull (e.getClickedInventory ()).getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }

        Player player = (Player) e.getWhoClicked ();
        UUID uuid = player.getUniqueId ();
        int pages = StockDataList.getNowBuyPage ().get (uuid);
        int clickedSlot = e.getSlot ();
        int num = clickedSlot + (pages - 1) * 45;
        int stockNum = StockDataList.getStocks ().size ();
        int maxPage = (int) Math.ceil ((double) stockNum / 45);
        SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();
        Economy eco = VaultAPI.getEconomy ();

        switch (clickedSlot) {
            case 45,46,47 -> {
                //ページの変更
                changePages (e,player,uuid,pages,clickedSlot,maxPage);
            }
            case 48 -> {
                //売れなかった在庫
                ReturnedChestGUI returnedStock = new ReturnedChestGUI ();
                player.openInventory (returnedStock.returned (player,1));
                StockDataList.putReturnPage (player.getUniqueId (),1);
                e.getClickedInventory ().close ();
            }
            case 49 -> {
                //自分の販売中の在庫
                NowStockChestGUI nowStock = new NowStockChestGUI ();
                player.openInventory (nowStock.nowPlayerStock (player,1));
                StockDataList.putNowStockPage (player.getUniqueId (),1);
                e.getClickedInventory ().close ();
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
                e.getClickedInventory ().close ();
            }
            case 53 -> {
                //閉じる
                e.getClickedInventory ().close ();
            }
            default -> {
                //クリックされた場所にアイテムがあるか
                if (num >= StockDataList.getStocks ().size ()) {
                    e.setCancelled (true);
                    return;
                }
                //出品者とクリックした人が同じ場合
                if (StockDataList.getStocks ().get (num).get (1).equals (uuid)) {
                    //出品者は自分が購入したアイテムは購入できないことを伝える
                    e.getClickedInventory ().setItem (clickedSlot,setTemplateItemData.getNoticeYoursItem ());
                    new BukkitRunnable () {
                        @Override
                        public void run () {
                            SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                            e.getClickedInventory ().setItem (clickedSlot,
                                    setStockItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                        }
                    }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                } else {
                    //購入するために必要な金額を持っていなかったら
                    if (!(Objects.requireNonNull (eco)
                            .getBalance (player.getPlayer ()) > (long) StockDataList.getStocks ().get (clickedSlot)
                            .get (2))) {
                        e.getClickedInventory ().setItem (clickedSlot,setTemplateItemData.getNoticeNoMoneyItem ());
                        new BukkitRunnable () {
                            @Override
                            public void run () {
                                SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                                e.getClickedInventory ().setItem (clickedSlot,
                                        setStockItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                            }
                        }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                        return;
                    }

                    //インベントリに空きがあったら
                    if (player.getInventory ().firstEmpty () != -1) {
                        BuyAcceptChestGUI buyAcceptChestGUI = new BuyAcceptChestGUI ();
                        player.openInventory (buyAcceptChestGUI.BuyAccept (player,num));
                        return;
                    }
                    //インベントリに空きがない時の処理
                    e.getClickedInventory ().setItem (clickedSlot,setTemplateItemData.getNoticeNoEmptyItem ());

                    new BukkitRunnable () {
                        @Override
                        public void run () {
                            SetStockItemMeta setStockItemMeta = new SetStockItemMeta ();
                            e.getClickedInventory ().setItem (clickedSlot,
                                    setStockItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                        }
                    }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                    return;
                }
            }
        }
        e.setCancelled (true);
    }
}
