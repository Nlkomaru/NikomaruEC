package dev.nikomaru.nikomaruec.gui.ec;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TerminalChestGUI {

		//アクセス用のGUIを作成する処理をする予定
		public Inventory Terminal(Player p) {
				Inventory gui = Bukkit.createInventory(p, 9, ChatColor.DARK_AQUA + "メニュー");

				//Menu Options(Items)

				ItemStack sell = new ItemStack(Material.EMERALD); //Kills the player

				ItemStack buy = new ItemStack(Material.GOLD_INGOT); //Fills the hunger bar

				ItemStack close = new ItemStack(Material.BARRIER); //Gives the player a weapon

				//Edit the items

				ItemMeta buy_meta = buy.getItemMeta();
				buy_meta.setDisplayName(ChatColor.GREEN + "物品購入所");
				ArrayList<String> buy_lore = new ArrayList<>();
				buy_lore.add(ChatColor.GRAY + "物品購入用へ移動");
				buy_meta.setLore(buy_lore);
				buy.setItemMeta(buy_meta);

				ItemMeta sell_meta = sell.getItemMeta();
				sell_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "物品販売所");
				ArrayList<String> sell_lore = new ArrayList<>();
				sell_lore.add(ChatColor.GRAY + "物品販売用へ移動");
				sell_meta.setLore(sell_lore);
				sell.setItemMeta(sell_meta);

				ItemMeta close_meta = close.getItemMeta();
				close_meta.setDisplayName(ChatColor.RED + "閉じる");
				ArrayList<String> close_lore = new ArrayList<>();
				close_lore.add(ChatColor.GRAY + "メニューを閉じる");
				close_meta.setLore(close_lore);
				close.setItemMeta(close_meta);

				gui.setItem(0, sell);
				gui.setItem(1, buy);
				gui.setItem(8, close);
				return gui;
		}
}
