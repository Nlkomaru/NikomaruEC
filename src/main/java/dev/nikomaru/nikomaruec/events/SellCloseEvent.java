package dev.nikomaru.nikomaruec.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

public class SellCloseEvent implements Listener {

		@EventHandler
		public void InventoryCloseEvent(InventoryCloseEvent e) {
				Player p = (Player) e.getPlayer();
				if (e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "物品販売所")) {

						if (e.getInventory() != null) {
								InventoryType inv = e.getInventory().getType();
								if (inv == InventoryType.CHEST) {

										if (e.getInventory().getItem(3) != null) {

												p.getInventory().addItem(e.getInventory().getItem(3));
										}
								}
						}
				}
		}
}
