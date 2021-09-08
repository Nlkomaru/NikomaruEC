package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {

		//アクセス用のGUIを作成する処理をする予定
		public Inventory Terminal(Player p) {
				Inventory gui = Bukkit.createInventory(p, 9, ChatColor.DARK_AQUA + "メニュー");

				SetItemData setItemData = new SetItemData();

				gui.setItem(0, setItemData.getSellItem());
				gui.setItem(1, setItemData.getBuyItem());
				gui.setItem(8, setItemData.getCloseItem());
				return gui;
		}
}
