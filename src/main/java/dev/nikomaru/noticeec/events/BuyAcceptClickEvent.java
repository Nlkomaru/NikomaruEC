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

import dev.nikomaru.noticeec.api.VaultAPI;
import dev.nikomaru.noticeec.files.history.CSVToHistory;
import dev.nikomaru.noticeec.files.stocks.WriteStockData;
import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
import dev.nikomaru.noticeec.utils.ChangeItemData;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.StockDataList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class BuyAcceptClickEvent implements Listener {
    //BuyAcceptGUIがクリックされたとき

    @EventHandler
    public void clickEvent (InventoryClickEvent e) {
        MakeGUI makegui = new MakeGUI ();

        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getBuyAcceptChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = Objects.requireNonNull (e.getClickedInventory ()).getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }

        Economy eco = VaultAPI.getEconomy ();
        Player player = (Player) e.getWhoClicked ();
        int slot = e.getSlot ();
        int i = StockDataList.getSelectNum ().get (player.getUniqueId ());
        ArrayList<Object> stock = StockDataList.getStocks ().get (i);
        ItemStack item = ChangeItemData.decode (stock.get (0).toString ());
        UUID uuid = (UUID) stock.get (1);
        long price = (long) stock.get (2);

        if (slot == 7) {
            //購入を承認したら
            //商品の渡す
            player.getInventory ().addItem (item);
            //購入者からお金を引く
            Objects.requireNonNull (eco).withdrawPlayer (player,price);
            //販売者にお金を渡す
            eco.depositPlayer (Bukkit.getOfflinePlayer (uuid),price);

            //csvに販売、購入履歴を記述
            CSVToHistory csvToHistory = new CSVToHistory ();
            try {
                csvToHistory.writePurchaseHistory (player.getUniqueId (),item,uuid,price);
                csvToHistory.writeSalesHistory (uuid,item,player.getUniqueId (),price);
            } catch (IOException ex) {
                ex.printStackTrace ();
            }
            //販売リストからアイテムを削除
            StockDataList.removeStocks (i);
            player.closeInventory ();
            //プレイヤーに購入したものの名前と金額を表示
            player.sendMessage (ChatColor.AQUA + (item.getI18NDisplayName () + "を" + Long.valueOf (price)
                    .toString () + "円でを購入しました"));
            //販売リストをファイルに書き込み保存
            WriteStockData writeStockData = new WriteStockData ();
            writeStockData.saveData ();
        } else if (slot == 8) {
            //閉じるボタンが押されたとき
            //インベントリを閉じる
            player.closeInventory ();
            //BuyGUIを開く
            BuyChestGUI buy = new BuyChestGUI ();
            player.openInventory (buy.Buy (player,StockDataList.getNowBuyPage ().get (player.getUniqueId ())));
        }
        e.setCancelled (true);
    }
}
