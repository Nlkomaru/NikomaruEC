package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class SellChestGUI {

		//販売用のGUIを作成する処理をする予定
		public Inventory Sell(Player p) {

				Inventory gui = Bukkit.createInventory(p, 9, ChatColor.LIGHT_PURPLE + "物品販売所");

				SetItemData setItemData = new SetItemData();

				gui.setItem(0, setItemData.getSetItemGlassItem());
				gui.setItem(1, setItemData.getSetItemGlassItem());
				gui.setItem(2, setItemData.getSetItemGlassItem());
				gui.setItem(4, setItemData.getSetItemGlassItem());
				gui.setItem(5, setItemData.getSetItemGlassItem());
				gui.setItem(6, setItemData.getSetItemGlassItem());
				gui.setItem(7, setItemData.getAcceptItem());
				gui.setItem(8, setItemData.getCloseItem());

				return gui;
		}

}