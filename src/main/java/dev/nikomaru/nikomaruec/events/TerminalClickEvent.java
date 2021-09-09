package dev.nikomaru.nikomaruec.events;

import static dev.nikomaru.nikomaruec.utils.StockDataList.getNowBuyPage;

import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.SellChestGUI;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class TerminalClickEvent implements Listener {

	//いろいろなところにアクセスできるGUIのクリックを処理する予定
	@EventHandler public void clickEvent (InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked ();
		if (e.getView ().title ().equals ((Component.text ("メニュー", TextColor.color (100, 149, 237))))) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {

					if (e.getSlot () == 0) {
						BuyChestGUI buy = new BuyChestGUI ();
						p.openInventory (buy.Buy (p, 1));
						getNowBuyPage ().put (p.getUniqueId (), 1);
						e.setCancelled (true);
					} else if (e.getSlot () == 1) {
						SellChestGUI sell = new SellChestGUI ();
						p.openInventory (sell.Sell (p));
						e.setCancelled (true);
						e.getClickedInventory ().close ();
					} else if (e.getSlot () == 8) {
						p.closeInventory ();
						e.setCancelled (true);
						e.getClickedInventory ().close ();
					} else {
						e.setCancelled (true);
					}
				}
			}

		}
	}
}
