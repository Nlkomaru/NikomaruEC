package dev.nikomaru.nikomaruec.gui.ec;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class buyChestGUI {
	
	//購入用のGUIを作成する処理をする予定
	public static List<Object> stock = new ArrayList<>();
	
	public static List<Object> getStock() {
		return stock;
	}
	public Inventory Buy(Player p) {
		//
		Inventory gui = Bukkit.createInventory(p, 54, ChatColor.AQUA + "Buy GUI");
		//        int i = 0;
		//                int num = (pages - 1) * 45;
		//                while (i < num) {
		//        ifで日付チェック
		//        アイテムをgui.setItem(i,stock(i))でセット
		//        whileなのでiを足す
		//                    i++;
		//                }
		//
		return gui;
	}
	
}
