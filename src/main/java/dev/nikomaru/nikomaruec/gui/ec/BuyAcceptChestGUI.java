package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class BuyAcceptChestGUI {
	public Inventory BuyAccept (Player p,int i) {
		
		MakeGUI makegui = new MakeGUI ();
		Inventory gui = Bukkit.createInventory (p,9,makegui.getBuyAcceptChest ());
		StockDataList.putSelectNum (p.getUniqueId (),i);
		
		SetItemData setItemData = new SetItemData ();
		GetItemMeta getItemMeta = new GetItemMeta ();
		
		List<Object> stock = StockDataList.getStocks ().get (i);
		
		
		gui.setItem (0,setItemData.getBlankGlassItem ());
		gui.setItem (1,setItemData.getBlankGlassItem ());
		gui.setItem (2,setItemData.getBlankGlassItem ());
		gui.setItem (3,getItemMeta.setItemMeta (stock));
		gui.setItem (4,setItemData.getBlankGlassItem ());
		gui.setItem (5,setItemData.getBlankGlassItem ());
		gui.setItem (6,setItemData.getBlankGlassItem ());
		gui.setItem (7,setItemData.getAcceptItem ());
		gui.setItem (8,setItemData.getDenyItem ());
		
		
		return gui;
	}
}
