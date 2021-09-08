package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.MakeList;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyChestGUI {

		//購入用のGUIを作成する処理をする予定

		SetItemData setItemData = new SetItemData();

		public Inventory Buy(Player p, int pages) {

				Inventory gui = Bukkit.createInventory(p, 54, ChatColor.GREEN + "物品購入所");
				int i = 0;
				int num = 45;
				int stockSize = MakeList.getStocks().size();

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
								gui.setItem(i, setItemData.getNoDataGlassItem());
								i++;
						}
				}

				gui.setItem(45, setItemData.getPrevItem());
				gui.setItem(46, setItemData.getReloadItem());
				gui.setItem(47, setItemData.getNextItem());
				gui.setItem(48, setItemData.getLeftoversItem());
				gui.setItem(49, setItemData.getStoreItem());
				gui.setItem(50, setItemData.getBuyHistoryItem());
				gui.setItem(51, setItemData.getSellHistoryItem());
				gui.setItem(52, setItemData.getTerminalItem());
				gui.setItem(53, setItemData.getCloseItem());

				return gui;
		}
}
