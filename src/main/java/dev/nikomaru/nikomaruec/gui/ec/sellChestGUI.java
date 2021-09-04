package dev.nikomaru.nikomaruec.gui.ec;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class sellChestGUI {
	
	//販売用のGUIを作成する処理をする予定
	public Inventory Sell(Player p) {
		
		Inventory gui = Bukkit.createInventory(p, 9, ChatColor.RED + "Sell GUI");
		
		//Menu Options(Items)
		
		ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE); //Kills the player
		
		ItemStack accept = new ItemStack(Material.LIME_WOOL); //Fills the hunger bar
		
		ItemStack close = new ItemStack(Material.BARRIER); //Gives the player a weapon
		
		//Edit the items
		
		ItemMeta glass_meta = glass.getItemMeta();
		ArrayList<String> glass_lore = new ArrayList<>();
		glass_lore.add(ChatColor.BLUE + "何もないところに商品をセットしてください");
		glass_meta.setLore(glass_lore);
		glass.setItemMeta(glass_meta);
		
		ItemMeta accept_meta = accept.getItemMeta();
		ArrayList<String> accept_lore = new ArrayList<>();
		accept_lore.add(ChatColor.RED + "決定");
		accept_meta.setLore(accept_lore);
		accept.setItemMeta(accept_meta);
		
		ItemMeta close_meta = close.getItemMeta();
		ArrayList<String> close_lore = new ArrayList<>();
		close_lore.add(ChatColor.GOLD + "閉じる");
		close_meta.setLore(close_lore);
		close.setItemMeta(close_meta);
		
		gui.setItem(0, glass);
		gui.setItem(1, glass);
		gui.setItem(2, glass);
		gui.setItem(3, glass);
		gui.setItem(5, glass);
		gui.setItem(6, glass);
		gui.setItem(7, accept);
		gui.setItem(8, close);
		
		return gui;
	}
	
}