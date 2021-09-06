package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.makeList;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class buyChestGUI {
	
	//購入用のGUIを作成する処理をする予定
	
	
	public Inventory Buy(Player p, Integer pages) {
		
		Inventory gui = Bukkit.createInventory(p, 54, ChatColor.GREEN + "物品購入所");
		int i = 0;
		int num = (pages - 1) * 45+45;
		int size = makeList.getStocks().size();
		
		System.out.println(size);
		
		ZonedDateTime nowTime = ZonedDateTime.now();
		
		ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE); //Kills the player
		
		ItemMeta glass_meta = glass.getItemMeta();
		glass_meta.setDisplayName(ChatColor.WHITE + "ここには何もありません");
		ArrayList<String> glass_lore = new ArrayList<>();
		glass_lore.add(ChatColor.YELLOW + "ここには何もありません");
		glass_meta.setLore(glass_lore);
		glass.setItemMeta(glass_meta);
		
		while (i < num) {
		
			if (i < size) {
				
				List<Object> stock = makeList.getStocks().get(i);
				
					
					Player Seller = (Player) Bukkit.getOfflinePlayer((UUID) stock.get(1));
//					SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH");
//					String limitTime = format.format(stock.get(4));
					
					// {itemStack} {player uuid} {price} {description} {time}
				
					
					ItemStack merchandise = (ItemStack) stock.get(0);
					ItemMeta merchandise_meta = merchandise.getItemMeta();
					ArrayList<String> merchandise_lore = new ArrayList<>();
					
					
					merchandise_lore.add(ChatColor.YELLOW + "出品者: " +(ChatColor.WHITE + Seller.getName()));
					merchandise_lore.add(ChatColor.YELLOW + "金額  : " + (ChatColor.WHITE + String.valueOf(stock.get(2))));
//					merchandise_lore.add(ChatColor.YELLOW + "期限  : " + (ChatColor.WHITE + limitTime));
					merchandise_lore.add(ChatColor.YELLOW + "説明  : " + (ChatColor.WHITE + (String)stock.get(3)));
					
					merchandise_meta.setLore(merchandise_lore);
					merchandise.setItemMeta(merchandise_meta);
					
					gui.setItem(i, merchandise);
					
					i++;
				
			} else {
				gui.setItem(i, glass);
				i++;
			}
			
			
		}
		
		return gui;
	}
	
	
}
