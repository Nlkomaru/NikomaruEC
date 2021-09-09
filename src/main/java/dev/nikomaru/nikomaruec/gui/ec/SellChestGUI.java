package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.SetItemData;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellChestGUI {

	//販売用のGUIを作成する処理をする予定
	public Inventory Sell (Player p) {

		Inventory gui = Bukkit.createInventory (p, 9, (Component.text ("物品販売所", TextColor.color (251, 107, 255))));

		SetItemData setItemData = new SetItemData ();

		gui.setItem (0, setItemData.getSetItemGlassItem ());
		gui.setItem (1, setItemData.getSetItemGlassItem ());
		gui.setItem (2, setItemData.getSetItemGlassItem ());
		gui.setItem (4, setItemData.getSetItemGlassItem ());
		gui.setItem (5, setItemData.getSetItemGlassItem ());
		gui.setItem (6, setItemData.getSetItemGlassItem ());
		gui.setItem (7, setItemData.getAcceptItem ());
		gui.setItem (8, setItemData.getCloseItem ());

		return gui;
	}

}