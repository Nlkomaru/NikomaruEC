package dev.nikomaru.nikomaruec.events;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        if (e.getView ().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Custom GUI")) {
            e.setCancelled (true);
        }
    }
}
