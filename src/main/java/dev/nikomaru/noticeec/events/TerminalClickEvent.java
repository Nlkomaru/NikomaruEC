package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.gui.ec.BuyChestGUI;
import dev.nikomaru.noticeec.gui.ec.SellChestGUI;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

public class TerminalClickEvent implements Listener {
	
	//いろいろなところにアクセスできるGUIのクリックを処理する予定
	@EventHandler
	public void clickEvent (@NotNull InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked ();
		MakeGUI makegui = new MakeGUI ();
		if (e.getView ().title ().equals ((makegui.getTerminalChest ()))) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {
					
					if (e.getSlot () == 0) {
						BuyChestGUI buy = new BuyChestGUI ();
						p.openInventory (buy.Buy (p,1));
						StockDataList.getNowBuyPage ().put (p.getUniqueId (),1);
					}
					else if (e.getSlot () == 1) {
						SellChestGUI sell = new SellChestGUI ();
						p.openInventory (sell.Sell (p));
						e.getClickedInventory ().close ();
					}
					else if (e.getSlot () == 8) {
						p.closeInventory ();
						e.getClickedInventory ().close ();
					}
					e.setCancelled (true);
				}
				
			}
			
		}
	}
}
