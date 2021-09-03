package dev.nikomaru.nikomaruec.events.click;

import dev.nikomaru.nikomaruec.gui.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.SellChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class TerminalClickEvent implements Listener {
    //いろいろなところにアクセスできるGUIのクリックを処理する予定
    @EventHandler public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle ().equalsIgnoreCase (ChatColor.AQUA + "Custom GUI")
                && e.getClickedInventory ().getType () == InventoryType.CHEST) {

            if (e.getSlot () == 0) {
                BuyChestGUI buy = new BuyChestGUI ();
                p.openInventory (buy.Buy (p));
            } else if (e.getSlot () == 1) {
                SellChestGUI sell = new SellChestGUI ();
                p.openInventory (sell.Sell (p));
            } else if (e.getSlot () == 8) {
                p.closeInventory ();
            }
            e.setCancelled (true);
        }
    }
}
