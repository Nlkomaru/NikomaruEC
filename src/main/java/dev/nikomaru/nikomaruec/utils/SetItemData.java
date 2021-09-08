package dev.nikomaru.nikomaruec.utils;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetItemData {

		public ItemStack getPrevItem() {
				//前のページ
				ItemStack prev = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta prev_meta = prev.getItemMeta();
				prev_meta.setDisplayName(ChatColor.RED + "前のページに戻る");
				prev.setItemMeta(prev_meta);
				return prev;
		}

		public ItemStack getReloadItem() {
				//ページを更新する
				ItemStack reload = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
				ItemMeta reload_meta = reload.getItemMeta();
				reload_meta.setDisplayName(ChatColor.GREEN + "ページを更新する");
				reload.setItemMeta(reload_meta);
				return reload;
		}

		public ItemStack getNextItem() {
				//次のページ
				ItemStack next = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
				ItemMeta next_meta = next.getItemMeta();
				next_meta.setDisplayName(ChatColor.AQUA + "次のページへ進む");
				next.setItemMeta(next_meta);
				return next;
		}

		public ItemStack getLeftoversItem() {
				//売れなかった在庫
				ItemStack leftovers = new ItemStack(Material.CHEST);
				ItemMeta leftovers_meta = leftovers.getItemMeta();
				leftovers_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "返却された在庫");
				leftovers.setItemMeta(leftovers_meta);
				return leftovers;
		}

		public ItemStack getStoreItem() {
				//出品中の在庫
				ItemStack store = new ItemStack(Material.ENDER_CHEST);
				ItemMeta store_meta = store.getItemMeta();
				store_meta.setDisplayName(ChatColor.YELLOW + "出品中の在庫");
				store.setItemMeta(store_meta);
				return store;
		}

		public ItemStack getBuyHistoryItem() {
				//購入履歴
				ItemStack buyHistory = new ItemStack(Material.KNOWLEDGE_BOOK);
				ItemMeta buyHistory_meta = buyHistory.getItemMeta();
				buyHistory_meta.setDisplayName(ChatColor.DARK_GREEN + "購入履歴を見る");
				buyHistory.setItemMeta(buyHistory_meta);
				return buyHistory;
		}

		public ItemStack getSellHistoryItem() {
				//販売履歴
				ItemStack sellHistory = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta sellHistory_meta = sellHistory.getItemMeta();
				sellHistory_meta.setDisplayName(ChatColor.DARK_PURPLE + "販売履歴を見る");
				sellHistory.setItemMeta(sellHistory_meta);
				return sellHistory;
		}

		public ItemStack getTerminalItem() {
				//ターミナルに戻る
				ItemStack terminal = new ItemStack(Material.NETHER_STAR);
				ItemMeta terminal_meta = terminal.getItemMeta();
				terminal_meta.setDisplayName(ChatColor.BLUE + "ターミナルに戻る");
				terminal.setItemMeta(terminal_meta);
				return terminal;
		}

		public ItemStack getCloseItem() {
				//ページを閉じる
				ItemStack close = new ItemStack(Material.BARRIER);
				ItemMeta close_meta = close.getItemMeta();
				close_meta.setDisplayName(ChatColor.RED + "閉じる");
				ArrayList<String> close_lore = new ArrayList<>();
				close_lore.add(ChatColor.GRAY + "ページを閉じる");
				close_meta.setLore(close_lore);
				close.setItemMeta(close_meta);
				return close;
		}

		public ItemStack getNoDataGlassItem() {
				//データがないことを知らせるガラス
				ItemStack glass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta glass_meta = glass.getItemMeta();
				glass_meta.setDisplayName(ChatColor.WHITE + "ここには何もありません");
				ArrayList<String> glass_lore = new ArrayList<>();
				glass_lore.add(ChatColor.YELLOW + "これは商品ではありません");
				glass_meta.setLore(glass_lore);
				glass.setItemMeta(glass_meta);

				return glass;
		}

		public ItemStack getBuyItem() {
				ItemStack buy = new ItemStack(Material.EMERALD);
				ItemMeta buy_meta = buy.getItemMeta();
				buy_meta.setDisplayName(ChatColor.GREEN + "物品購入所");
				ArrayList<String> buy_lore = new ArrayList<>();
				buy_lore.add(ChatColor.GRAY + "物品購入用へ移動");
				buy_meta.setLore(buy_lore);
				buy.setItemMeta(buy_meta);

				return buy;
		}

		public ItemStack getSellItem() {
				ItemStack sell = new ItemStack(Material.GOLD_INGOT);
				ItemMeta sell_meta = sell.getItemMeta();
				sell_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "物品販売所");
				ArrayList<String> sell_lore = new ArrayList<>();
				sell_lore.add(ChatColor.GRAY + "物品販売用へ移動");
				sell_meta.setLore(sell_lore);
				sell.setItemMeta(sell_meta);

				return sell;
		}

}
