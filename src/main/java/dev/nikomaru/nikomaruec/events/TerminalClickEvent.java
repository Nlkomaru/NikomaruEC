package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.GUI.BuyGUI;
import dev.nikomaru.nikomaruec.GUI.SellGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TerminalClickEvent implements Listener {

    @EventHandler
    public void clickEvent(InventoryClickEvent e){

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle().equalsIgnoreCase(ChatColor.AQUA + "Custom GUI")) {
            if(e.getSlot ()==0){
                BuyGUI buy = new BuyGUI ();
                p.openInventory (buy.Buy(p));
            }else if(e.getSlot ()==1){
                SellGUI sell = new SellGUI ();
                p.openInventory (sell.Sell(p));
            }else if(e.getSlot ()==8){
                p.closeInventory();
            }
            e.setCancelled (true);
        }
    }
}
