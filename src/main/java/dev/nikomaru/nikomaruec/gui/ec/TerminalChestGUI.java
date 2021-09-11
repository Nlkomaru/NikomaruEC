package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.SetItemData;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {

	//アクセス用のGUIを作成する処理をする予定
	public Inventory Terminal (Player p) {
		Inventory gui = Bukkit.createInventory (p, 9, (Component.text ("メニュー", TextColor.color (100, 149, 237))));

		SetItemData setItemData = new SetItemData ();

		gui.setItem (0, setItemData.getSellItem ());
		gui.setItem (1, setItemData.getBuyItem ());
		gui.setItem (8, setItemData.getCloseItem ());
		return gui;
	}
}
