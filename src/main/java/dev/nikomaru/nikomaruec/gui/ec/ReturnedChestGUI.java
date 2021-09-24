package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.ChangeItemData;
import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

public class ReturnedChestGUI {
	final SetItemData setItemData = new SetItemData ();
	
	public Inventory returned (Player p,int pages) {
		MakeGUI makegui = new MakeGUI ();
		Inventory gui = Bukkit.createInventory (p,54,makegui.getReturnedChest ());
		int i = 0;
		int num = 45;
		NikomaruEC.getReturnStocks ().computeIfAbsent (p.getUniqueId (),k -> new ArrayList<> ());
		int stockSize = NikomaruEC.getReturnStocks ().get (p.getUniqueId ()).size ();
		while (i < num) {
			
			if ((pages - 1) * 45 + i < stockSize) {
				GetItemMeta getItemMeta = new GetItemMeta ();
				
				List<List<Object>> stock = NikomaruEC.getReturnStocks ().get (p.getUniqueId ());
				List<Object> returned = stock.get ((pages - 1) * 45 + i);
				List<Object> displayReturned = new ArrayList<> ();
				displayReturned.add (ChangeItemData.decode(returned.get (0).toString ()));
				displayReturned.add (returned.get (1));
				displayReturned.add (returned.get (2));
				displayReturned.add (returned.get (3));
				displayReturned.add (returned.get (4));
				gui.setItem (i,getItemMeta.setReturnedItemMeta (displayReturned));
				
			}else {
				gui.setItem (i,setItemData.getNoDataGlassItem ());
				
			}
			i++;
		}
		
		gui.setItem (45,setItemData.getPrevItem ());
		gui.setItem (46,setItemData.getReloadItem ());
		gui.setItem (47,setItemData.getNextItem ());
		gui.setItem (48,setItemData.getBuyItem ());
		gui.setItem (49,setItemData.getStoreItem ());
		gui.setItem (50,setItemData.getBuyHistoryItem ());
		gui.setItem (51,setItemData.getSellHistoryItem ());
		gui.setItem (52,setItemData.getTerminalItem ());
		gui.setItem (53,setItemData.getCloseItem ());
		
		return gui;
	}
}
