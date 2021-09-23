package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.files.stocks.WriteStockData;
import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class BuyAcceptClickEvent implements Listener {
    // {itemStack} {player uuid} {price} {description} {time}
    
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {
        MakeGUI makegui = new MakeGUI ();
        
        if (e.getView ().title ().equals (makegui.getBuyAcceptChest ())) {
            Economy eco = VaultAPI.getEconomy ();
            Player p = (Player) e.getWhoClicked ();
            int slot = e.getSlot ();
            int i = StockDataList.getSelectNum ().get (p.getUniqueId ());
            List<Object> stock = NikomaruEC.getStocks ().get (i);
            ItemStack item = (ItemStack) stock.get (0);
            UUID uuid = (UUID) stock.get (1);
            long price = (long) stock.get (2);
            if (slot == 7) {
                p.getInventory ().addItem (item);
                Objects.requireNonNull (eco).withdrawPlayer (p,price);
                eco.depositPlayer (Bukkit.getOfflinePlayer (uuid),price);
                NikomaruEC.getStocks ().remove (i);
                p.closeInventory ();
                p.sendMessage (ChatColor.AQUA + (Long.valueOf (price).toString () + "円") + "で" + ChatColor.GREEN + (Bukkit.getOfflinePlayer (uuid).getName ()) + "の" + ChatColor.GOLD + (item.displayName ()) + "を購入しました");
                WriteStockData writeStockData = new WriteStockData ();
                writeStockData.saveData ();
            }
            else if (slot == 8) {
                p.closeInventory ();
                BuyChestGUI buy = new BuyChestGUI ();
                p.openInventory (buy.Buy (p,StockDataList.getNowBuyPage ().get (p.getUniqueId ())));
            }
            e.setCancelled (true);
        }
    }
}
