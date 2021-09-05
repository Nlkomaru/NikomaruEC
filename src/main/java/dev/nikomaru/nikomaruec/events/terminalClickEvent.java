package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.gui.ec.buyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.sellChestGUI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class terminalClickEvent implements Listener {
	
	//いろいろなところにアクセスできるGUIのクリックを処理する予定
	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_AQUA + "メニュー")) {
			if (e.getClickedInventory() != null) {
				InventoryType inv = e.getClickedInventory().getType();
				if (inv == InventoryType.CHEST) {
					
					if (e.getSlot() == 0) {
						buyChestGUI buy = new buyChestGUI();
						p.openInventory(buy.Buy(p));
						e.setCancelled(true);
					} else if (e.getSlot() == 1) {
						sellChestGUI sell = new sellChestGUI();
						p.openInventory(sell.Sell(p));
						e.setCancelled(true);
					} else if (e.getSlot() == 8) {
						p.closeInventory();
						e.setCancelled(true);
					} else {
						e.setCancelled(true);
					}
				}
			}
			
		}
	}
}
