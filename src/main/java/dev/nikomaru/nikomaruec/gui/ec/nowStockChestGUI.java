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
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class nowStockChestGUI {

		// 未来へのヒント indexOf()を使用すること

		SetItemData setItemData = new SetItemData();

		public Inventory nowPlayerStock(Player p, int pages) {

				Inventory gui = Bukkit.createInventory(p, 54, ChatColor.DARK_PURPLE + "出品中の在庫");
				int i = 0;
				int num = 45;


				while (i < num) {
						int itemNum = (pages - 1) * 45 + i;
						if (MakeList.getStocks().size() > itemNum) {
								List<Object> stock = MakeList.getStocks().get(itemNum);

								Player Seller = (Player) Bukkit.getOfflinePlayer((UUID) stock.get(1));
								System.out.println(1);
								if ((UUID) stock.get(1) == p.getUniqueId()) {

										System.out.println(2);
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
								}
						} else {
								gui.setItem(i,setItemData.getNoDataGlassItem());
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
