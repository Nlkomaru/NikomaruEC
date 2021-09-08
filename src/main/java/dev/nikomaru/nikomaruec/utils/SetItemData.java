package dev.nikomaru.nikomaruec.utils;

import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetItemData {

		ItemStack close = new ItemStack(Material.BARRIER);

		public ItemStack getPrevItem() {
				//前のページに戻るためのガラス
				ItemStack prev = new ItemStack(Material.RED_STAINED_GLASS_PANE);
				ItemMeta prev_meta = prev.getItemMeta();
				prev_meta.setDisplayName(ChatColor.RED + "前のページに戻る");
				prev.setItemMeta(prev_meta);
				return prev;
		}

		public ItemStack getReloadItem() {
				//ページを更新するためのガラス
				ItemStack reload = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
				ItemMeta reload_meta = reload.getItemMeta();
				reload_meta.setDisplayName(ChatColor.GREEN + "ページを更新する");
				reload.setItemMeta(reload_meta);
				return reload;
		}

		public ItemStack getNextItem() {
				//次のページに進むためのガラス
				ItemStack next = new ItemStack(Material.BLUE_STAINED_GLASS_PANE);
				ItemMeta next_meta = next.getItemMeta();
				next_meta.setDisplayName(ChatColor.AQUA + "次のページへ進む");
				next.setItemMeta(next_meta);
				return next;
		}

		public ItemStack getLeftoversItem() {
				//売れなかった在庫を見るためのチェスト
				ItemStack leftovers = new ItemStack(Material.CHEST);
				ItemMeta leftovers_meta = leftovers.getItemMeta();
				leftovers_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "返却された在庫");
				leftovers.setItemMeta(leftovers_meta);
				return leftovers;
		}

		public ItemStack getStoreItem() {
				//出品中の在庫を見るためのチェスト
				ItemStack store = new ItemStack(Material.ENDER_CHEST);
				ItemMeta store_meta = store.getItemMeta();
				store_meta.setDisplayName(ChatColor.YELLOW + "出品中の在庫");
				store.setItemMeta(store_meta);
				return store;
		}

		public ItemStack getBuyHistoryItem() {
				//購入履歴表示用の本
				ItemStack buyHistory = new ItemStack(Material.KNOWLEDGE_BOOK);
				ItemMeta buyHistory_meta = buyHistory.getItemMeta();
				buyHistory_meta.setDisplayName(ChatColor.DARK_GREEN + "購入履歴を見る");
				buyHistory.setItemMeta(buyHistory_meta);
				return buyHistory;
		}

		public ItemStack getSellHistoryItem() {
				//販売履歴表示用の本
				ItemStack sellHistory = new ItemStack(Material.ENCHANTED_BOOK);
				ItemMeta sellHistory_meta = sellHistory.getItemMeta();
				sellHistory_meta.setDisplayName(ChatColor.DARK_PURPLE + "販売履歴を見る");
				sellHistory.setItemMeta(sellHistory_meta);
				return sellHistory;
		}

		public ItemStack getTerminalItem() {
				//ターミナルに戻るためのネザースター
				ItemStack terminal = new ItemStack(Material.NETHER_STAR);
				ItemMeta terminal_meta = terminal.getItemMeta();
				terminal_meta.setDisplayName(ChatColor.BLUE + "ターミナルに戻る");
				terminal.setItemMeta(terminal_meta);
				return terminal;
		}

		public ItemStack getCloseItem() {
				//ページを閉じることを知らせるバリアブロック
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
				ItemStack noDataGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta noDataGlass_meta = noDataGlass.getItemMeta();
				noDataGlass_meta.setDisplayName(ChatColor.WHITE + "ここには何もありません");
				ArrayList<String> noDataGlass_lore = new ArrayList<>();
				noDataGlass_lore.add(ChatColor.YELLOW + "これは商品ではありません");
				noDataGlass_meta.setLore(noDataGlass_lore);
				noDataGlass.setItemMeta(noDataGlass_meta);

				return noDataGlass;
		}

		public ItemStack getBuyItem() {
				//物品購入所のボタンのエメラルド
				ItemStack buy = new ItemStack(Material.EMERALD);
				ItemMeta buy_meta = buy.getItemMeta();
				buy_meta.setDisplayName(ChatColor.GREEN + "物品購入所");
				ArrayList<String> buy_lore = new ArrayList<>();
				buy_lore.add(ChatColor.GRAY + "物品購入所へ移動");
				buy_meta.setLore(buy_lore);
				buy.setItemMeta(buy_meta);

				return buy;
		}

		public ItemStack getSellItem() {
				//物品販売所所のボタンの金インゴット
				ItemStack sell = new ItemStack(Material.GOLD_INGOT);
				ItemMeta sell_meta = sell.getItemMeta();
				sell_meta.setDisplayName(ChatColor.LIGHT_PURPLE + "物品販売所");
				ArrayList<String> sell_lore = new ArrayList<>();
				sell_lore.add(ChatColor.GRAY + "物品販売所へ移動");
				sell_meta.setLore(sell_lore);
				sell.setItemMeta(sell_meta);

				return sell;
		}


		public ItemStack getSetItemGlassItem() {
				//アイテムをセットすることを伝えるガラス
				ItemStack setItemGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
				ItemMeta setItem_meta = setItemGlass.getItemMeta();
				setItem_meta.setDisplayName(ChatColor.WHITE + "何もないところに商品をセットしてください");
				ArrayList<String> setItemGlass_lore = new ArrayList<>();
				setItemGlass_lore.add(ChatColor.BLUE + "セットしたら黄緑の羊毛をクリックして決定してください");
				setItem_meta.setLore(setItemGlass_lore);
				setItemGlass.setItemMeta(setItem_meta);
				return setItemGlass;
		}

		public ItemStack getAcceptItem() {
				//決定用ボタンの羊毛
				ItemStack accept = new ItemStack(Material.LIME_WOOL);
				ItemMeta accept_meta = accept.getItemMeta();
				accept_meta.setDisplayName(ChatColor.GREEN + "決定");
				ArrayList<String> accept_lore = new ArrayList<>();
				accept_lore.add(ChatColor.GOLD + "決定する場合こちら");
				accept_meta.setLore(accept_lore);
				accept.setItemMeta(accept_meta);
				return accept;
		}


}
