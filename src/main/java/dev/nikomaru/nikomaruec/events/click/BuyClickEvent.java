package dev.nikomaru.nikomaruec.events.click;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyClickEvent implements Listener {
    //購入用のアイテムがクリックされたら購入用GUIに飛ぶ処理をする予定
    @EventHandler public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle ().equalsIgnoreCase (ChatColor.RED + "Buy GUI")) {
            int i = e.getSlot ();

            e.setCancelled (true);
        }
    }
}