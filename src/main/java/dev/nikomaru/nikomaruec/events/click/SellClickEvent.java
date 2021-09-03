package dev.nikomaru.nikomaruec.events.click;

import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class SellClickEvent implements Listener {
    //販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
    @EventHandler public void clickEvent (InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked ();
        if (e.getView ().getTitle ().equalsIgnoreCase (ChatColor.RED + "Sell GUI")) {
            int slot = e.getSlot ();
            InventoryType inv = e.getClickedInventory ().getType ();
            if (inv == InventoryType.CHEST) {
                if (0 <= slot && slot <= 2 || 4 <= slot && slot <= 8) {

                    if (slot == 8) {
                        p.closeInventory ();

                    } else if (slot == 7) {

                        ItemStack item = Objects.requireNonNull (e.getClickedInventory ()).getItem (4);
                        if (item != null) {
                            p.closeInventory ();
                            System.out.println (item);
                            ItemStack sell_item = item;
                        }
                        e.setCancelled (true);
                    }
                }

            }
        }

    }
}
