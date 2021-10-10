/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.api.VaultAPI;
import dev.nikomaru.noticeec.gui.ec.*;
import dev.nikomaru.noticeec.utils.GetItemMeta;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetItemData;
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


    //購入用のアイテムがクリックされたら購入用GUIに飛ぶ処理をする予定
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        MakeGUI makegui = new MakeGUI ();
        if (!(e.getView ().title ().equals (makegui.getBuyChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = Objects.requireNonNull (e.getClickedInventory ()).getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }

        UUID uuid = p.getUniqueId ();
        int pages = StockDataList.getNowBuyPage ().get (uuid);
        int clickedSlot = e.getSlot ();
        int num = clickedSlot + (pages - 1) * 45;
        int stockNum = StockDataList.getStocks ().size ();
        int maxPage = (int) Math.ceil ((double) stockNum / 45);
        SetItemData setItemData = new SetItemData ();
        Economy eco = VaultAPI.getEconomy ();
        //1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる

        switch (clickedSlot) {
            case 45,46,47 -> {
                //ページの変更
                changePages (e,p,uuid,pages,clickedSlot,maxPage);
            }
            case 48 -> {
                //売れなかった在庫
                ReturnedChestGUI returnedStock = new ReturnedChestGUI ();
                p.openInventory (returnedStock.returned (p,1));
                StockDataList.putReturnPage (p.getUniqueId (),1);
            }
            case 49 -> {
                //自分の販売中の在庫
                NowStockChestGUI nowStock = new NowStockChestGUI ();
                p.openInventory (nowStock.nowPlayerStock (p,1));
                StockDataList.putNowStockPage (p.getUniqueId (),1);
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
                if (num >= StockDataList.getStocks ().size ()) {
                    e.setCancelled (true);
                    return;
                }
                if (StockDataList.getStocks ().get (num).get (1).equals (uuid)) {
                    e.getClickedInventory ().setItem (clickedSlot,setItemData.getNoticeYoursItem ());
                    new BukkitRunnable () {
                        @Override
                        public void run () {
                            GetItemMeta getItemMeta = new GetItemMeta ();
                            e.getClickedInventory ().setItem (clickedSlot,
                                    getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                        }
                    }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                } else {
                    if (!(Objects.requireNonNull (eco).getBalance (p.getPlayer ()) > (long) StockDataList.getStocks ()
                            .get (clickedSlot).get (2))) {
                        e.getClickedInventory ().setItem (clickedSlot,setItemData.getNoticeNoMoneyItem ());
                        new BukkitRunnable () {
                            @Override
                            public void run () {
                                GetItemMeta getItemMeta = new GetItemMeta ();
                                e.getClickedInventory ().setItem (clickedSlot,
                                        getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                            }
                        }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                        return;
                    }

                    if (p.getInventory ().firstEmpty () != -1) {
                        BuyAcceptChestGUI buyAcceptChestGUI = new BuyAcceptChestGUI ();
                        p.openInventory (buyAcceptChestGUI.BuyAccept (p,num));
                        return;
                    }
                    e.getClickedInventory ().setItem (clickedSlot,setItemData.getNoticeNoEmptyItem ());

                    new BukkitRunnable () {
                        @Override
                        public void run () {
                            GetItemMeta getItemMeta = new GetItemMeta ();
                            e.getClickedInventory ().setItem (clickedSlot,
                                    getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
                        }
                    }.runTaskLater (NoticeEC.getPlugin (),20 * 2);
                    return;
                }
            }
        }
        e.setCancelled (true);
    }

    static void changePages (InventoryClickEvent e,Player p,UUID playerUUID,int pages,int i,int maxPage) {

        int change = 0;
        if (pages > 1 && i == 45) {
            change = -1;

        } else if (pages <= 1 && i == 47 && pages < maxPage) {

            change = 1;

        }

        StockDataList.putNowBuyPage (playerUUID,pages + change);
        BuyChestGUI buy = new BuyChestGUI ();
        p.openInventory (buy.Buy (p,pages + change));
        e.setCancelled (true);
    }
}
