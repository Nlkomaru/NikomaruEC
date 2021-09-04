package dev.nikomaru.nikomaruec.events.click;

import dev.nikomaru.nikomaruec.gui.ec.buyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.sellChestGUI;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class terminalClickEvent implements Listener {
    //いろいろなところにアクセスできるGUIのクリックを処理する予定
    @EventHandler public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle ().equalsIgnoreCase (ChatColor.AQUA + "Custom GUI")
                && Objects.requireNonNull (e.getClickedInventory ()).getType () == InventoryType.CHEST) {

            if (e.getSlot () == 0) {
                buyChestGUI buy = new buyChestGUI ();
                p.openInventory (buy.Buy (p, 0));
            } else if (e.getSlot () == 1) {
                sellChestGUI sell = new sellChestGUI ();
                p.openInventory (sell.Sell (p));
            } else if (e.getSlot () == 8) {
                p.closeInventory ();
            }
            e.setCancelled (true);
        }
    }
}
