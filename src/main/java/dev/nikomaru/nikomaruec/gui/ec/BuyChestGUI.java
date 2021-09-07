package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.MakeList;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
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

public class BuyChestGUI {

		//購入用のGUIを作成する処理をする予定


		public Inventory Buy(Player p, Integer pages) {

				Inventory gui = Bukkit.createInventory(p, 54, ChatColor.GREEN + "物品購入所");
				int i = 0;
				int num = 45;
				int stockSize = MakeList.getStocks().size();

				ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE); //Kills the player

				ItemMeta glass_meta = glass.getItemMeta();
				glass_meta.setDisplayName(ChatColor.WHITE + "ここには何もありません");
				ArrayList<String> glass_lore = new ArrayList<>();
				glass_lore.add(ChatColor.YELLOW + "これは商品ではありません");
				glass_meta.setLore(glass_lore);
				glass.setItemMeta(glass_meta);

				while (i < num) {

						if ((pages - 1) * 45 + i < stockSize) {
								List<Object> stock = MakeList.getStocks().get((pages - 1) * 45 + i);

								ZonedDateTime nowTime = ZonedDateTime.now();

								if (nowTime.isBefore((ZonedDateTime) stock.get(4))) {

										Player Seller = (Player) Bukkit.getOfflinePlayer((UUID) stock.get(1));

										DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
										String limitTime = format.format((ZonedDateTime) stock.get(4));

										// {itemStack} {player uuid} {price} {description} {time}

										ItemStack merchandise = (ItemStack) stock.get(0);
										ItemMeta merchandise_meta = merchandise.getItemMeta();
										ArrayList<String> merchandise_lore = new ArrayList<>();

										merchandise_lore.add(
												ChatColor.YELLOW + "出品者 : " + (ChatColor.WHITE + Seller.getName()));
										merchandise_lore.add(
												ChatColor.YELLOW + "金額   : " + (ChatColor.WHITE + String.valueOf(
														stock.get(2))));
										merchandise_lore.add(
												ChatColor.YELLOW + "期限   : " + (ChatColor.WHITE + limitTime));
										merchandise_lore.add(
												ChatColor.YELLOW + "説明   : " + (ChatColor.WHITE + (String) stock.get(3)));

										merchandise_meta.setLore(merchandise_lore);
										merchandise.setItemMeta(merchandise_meta);

										gui.setItem(i, merchandise);

										i++;
								} else {
										MakeList.getStocks().get((pages - 1) * 45 + i);
								}
						} else {
								gui.setItem(i, glass);
								i++;
						}
				}

				//前のページ
				ItemStack prev = new ItemStack(Material.RED_STAINED_GLASS_PANE); //Kills the player
				ItemMeta prev_meta = prev.getItemMeta();
				prev_meta.setDisplayName(ChatColor.RED + "前のページに戻る");
				prev.setItemMeta(prev_meta);

				//ページを更新する
				ItemStack reload = new ItemStack(Material.GREEN_STAINED_GLASS_PANE); //Kills the player
				ItemMeta reload_meta = reload.getItemMeta();
				reload_meta.setDisplayName(ChatColor.GREEN + "ページを更新する");
				reload.setItemMeta(reload_meta);

				//次のページ
				ItemStack next = new ItemStack(Material.BLUE_STAINED_GLASS_PANE); //Kills the player
				ItemMeta next_meta = next.getItemMeta();
				next_meta.setDisplayName(ChatColor.AQUA + "次のページへ進む");
				next.setItemMeta(next_meta);

				//売れなかった在庫
				ItemStack leftovers = new ItemStack(Material.CHEST); //Kills the player
				ItemMeta leftovers_meta = leftovers.getItemMeta();
				leftovers_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "返却された在庫");
				leftovers.setItemMeta(leftovers_meta);

				//出品中の在庫
				ItemStack store = new ItemStack(Material.ENDER_CHEST); //Kills the player
				ItemMeta store_meta = store.getItemMeta();
				store_meta.setDisplayName(ChatColor.YELLOW + "出品中の在庫");
				store.setItemMeta(store_meta);

				//購入履歴
				ItemStack buyHistory = new ItemStack(Material.KNOWLEDGE_BOOK); //Kills the player
				ItemMeta buyHistory_meta = buyHistory.getItemMeta();
				buyHistory_meta.setDisplayName(ChatColor.DARK_GREEN + "購入履歴を見る");
				buyHistory.setItemMeta(buyHistory_meta);

				//販売履歴
				ItemStack sellHistory = new ItemStack(Material.ENCHANTED_BOOK); //Kills the player
				ItemMeta sellHistory_meta = sellHistory.getItemMeta();
				sellHistory_meta.setDisplayName(ChatColor.DARK_PURPLE + "販売履歴を見る");
				sellHistory.setItemMeta(sellHistory_meta);

				//ターミナルに戻る
				ItemStack terminal = new ItemStack(Material.NETHER_STAR); //Kills the player
				ItemMeta terminal_meta = terminal.getItemMeta();
				next_meta.setDisplayName(ChatColor.BLUE + "ターミナルに戻る");
				terminal.setItemMeta(next_meta);

				//ページを閉じる
				ItemStack close = new ItemStack(Material.BARRIER); //Kills the player
				ItemMeta close_meta = close.getItemMeta();
				close_meta.setDisplayName(ChatColor.DARK_RED + "ページを閉じる");
				close.setItemMeta(close_meta);

				gui.setItem(45, prev);
				gui.setItem(46, reload);
				gui.setItem(47, next);
				gui.setItem(48, leftovers);
				gui.setItem(49, store);
				gui.setItem(50, buyHistory);
				gui.setItem(51, sellHistory);
				gui.setItem(52, terminal);
				gui.setItem(53, close);

				return gui;
		}
}
