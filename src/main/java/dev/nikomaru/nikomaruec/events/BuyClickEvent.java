package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.GUI.PriceAnvilGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyClickEvent implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle().equalsIgnoreCase(ChatColor.RED + "Buy GUI")) {
            if(e.getSlot ()==0){
                PriceAnvilGUI price = new PriceAnvilGUI ();
                p.openInventory (price.Price(p));
            }
            e.setCancelled (true);
        }
    }
}