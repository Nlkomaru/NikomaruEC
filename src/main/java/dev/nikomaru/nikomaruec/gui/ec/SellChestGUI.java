package dev.nikomaru.nikomaruec.gui.ec;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SellChestGUI {

		//販売用のGUIを作成する処理をする予定
		public Inventory Sell(Player p) {

				Inventory gui = Bukkit.createInventory(p, 9, ChatColor.LIGHT_PURPLE + "物品販売所");

				//Menu Options(Items)

				ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);

				ItemStack accept = new ItemStack(Material.LIME_WOOL);

				ItemStack close = new ItemStack(Material.BARRIER);

				//Edit the items

				ItemMeta glass_meta = glass.getItemMeta();
				glass_meta.setDisplayName(ChatColor.WHITE + "何もないところに商品をセットしてください");
				ArrayList<String> glass_lore = new ArrayList<>();
				glass_lore.add(ChatColor.BLUE + "セットしたら黄緑の羊毛をクリックして決定してください");
				glass_meta.setLore(glass_lore);
				glass.setItemMeta(glass_meta);

				ItemMeta accept_meta = accept.getItemMeta();
				accept_meta.setDisplayName(ChatColor.GREEN + "決定");
				ArrayList<String> accept_lore = new ArrayList<>();
				accept_lore.add(ChatColor.GOLD + "決定する場合こちら");
				accept_meta.setLore(accept_lore);
				accept.setItemMeta(accept_meta);

				ItemMeta close_meta = close.getItemMeta();
				close_meta.setDisplayName(ChatColor.WHITE + "閉じる");
				ArrayList<String> close_lore = new ArrayList<>();
				close_lore.add(ChatColor.GOLD + "閉じたい場合はこちら");
				close_meta.setLore(close_lore);
				close.setItemMeta(close_meta);

				gui.setItem(0, glass);
				gui.setItem(1, glass);
				gui.setItem(2, glass);
				gui.setItem(4, glass);
				gui.setItem(5, glass);
				gui.setItem(6, glass);
				gui.setItem(7, accept);
				gui.setItem(8, close);

				return gui;
		}

}