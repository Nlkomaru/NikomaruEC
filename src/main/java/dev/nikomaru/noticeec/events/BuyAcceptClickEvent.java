/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.api.VaultAPI;
import dev.nikomaru.noticeec.files.stocks.WriteStockData;
import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
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

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BuyAcceptClickEvent implements Listener {
    // {itemStack} {player uuid} {price} {description} {time}

    @EventHandler
    public void clickEvent (InventoryClickEvent e) {
        MakeGUI makegui = new MakeGUI ();

        if (!(e.getView ().title ().equals (makegui.getBuyAcceptChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        InventoryType inv = Objects.requireNonNull (e.getClickedInventory ()).getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }

        Economy eco = VaultAPI.getEconomy ();
        Player p = (Player) e.getWhoClicked ();
        int slot = e.getSlot ();
        int i = StockDataList.getSelectNum ().get (p.getUniqueId ());
        List<Object> stock = StockDataList.getStocks ().get (i);
        ItemStack item = (ItemStack) stock.get (0);
        UUID uuid = (UUID) stock.get (1);
        long price = (long) stock.get (2);


        if (slot == 7) {
            p.getInventory ().addItem (item);
            Objects.requireNonNull (eco).withdrawPlayer (p,price);
            eco.depositPlayer (Bukkit.getOfflinePlayer (uuid),price);
            StockDataList.removeStocks (i);
            p.closeInventory ();
            p.sendMessage (ChatColor.AQUA + (Long.valueOf (price)
                    .toString () + "円") + "で" + ChatColor.GREEN + (Bukkit.getOfflinePlayer (uuid)
                    .getName ()) + "の" + ChatColor.GOLD + (item.displayName ()) + "を購入しました");
            WriteStockData.saveData ();

        } else if (slot == 8) {
            p.closeInventory ();
            BuyChestGUI buy = new BuyChestGUI ();
            p.openInventory (buy.Buy (p,StockDataList.getNowBuyPage ().get (p.getUniqueId ())));
        }
        e.setCancelled (true);
    }
}
