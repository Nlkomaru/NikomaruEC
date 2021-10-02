package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellChestGUI {
	
	//販売用のGUIを作成する処理をする予定
	public Inventory Sell (Player p) {
		
		MakeGUI makegui = new MakeGUI ();
		Inventory gui = Bukkit.createInventory (p,9,makegui.getSellChest ());
		
		SetItemData setItemData = new SetItemData ();
		
		gui.setItem (0,setItemData.getSetItemGlassItem ());
		gui.setItem (1,setItemData.getSetItemGlassItem ());
		gui.setItem (2,setItemData.getSetItemGlassItem ());
		gui.setItem (4,setItemData.getSetItemGlassItem ());
		gui.setItem (5,setItemData.getSetItemGlassItem ());
		gui.setItem (6,setItemData.getSetItemGlassItem ());
		gui.setItem (7,setItemData.getAcceptItem ());
		gui.setItem (8,setItemData.getCloseItem ());
		
		return gui;
	}
	
}