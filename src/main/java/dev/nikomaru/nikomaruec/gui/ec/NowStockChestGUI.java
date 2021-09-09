package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.SetItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class NowStockChestGUI {

	// 未来へのヒント indexOf()を使用すること
	final SetItemData setItemData = new SetItemData ();

	public Inventory nowPlayerStock (Player p, int pages) {

		Inventory gui = Bukkit.createInventory (p, 54, Component.text ("出品中の在庫", TextColor.color (255, 0, 255)));

		int i = 0;
		int num = 45;

		while (i < num) {

			int itemNum = (pages - 1) * 45 + i;

			if (StockDataList.getStocks ().size () > itemNum) {
				List<Object> stock = StockDataList.getStocks ().get (itemNum);

				Player Seller = (Player) Bukkit.getOfflinePlayer ((UUID) stock.get (1));
				if (stock.get (1) == p.getUniqueId ()) {

					DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm");
					String limitTime = format.format ((ZonedDateTime) stock.get (4));

					// {itemStack} {player uuid} {price} {description} {time}

					ItemStack merchandise = (ItemStack) stock.get (0);
					ItemMeta merchandise_meta = merchandise.getItemMeta ();
					ArrayList<Component> merchandise_lore = new ArrayList<> ();

					merchandise_lore.add (Component.text ("出品者 : ", TextColor.color (255, 215, 0))
							.append (Component.text (Seller.getName (), TextColor.color (255, 255, 255))));

					merchandise_lore.add (Component.text ("金額   : ", TextColor.color (255, 215, 0))
							.append (Component.text (String.valueOf (stock.get (2)), TextColor.color (255, 255, 255))));

					merchandise_lore.add (Component.text ("期限   : ", TextColor.color (255, 215, 0))
							.append (Component.text (limitTime, TextColor.color (255, 255, 255))));

					merchandise_lore.add (Component.text ("説明   : ", TextColor.color (255, 215, 0))
							.append (Component.text (String.valueOf (stock.get (3)), TextColor.color (255, 255, 255))));

					merchandise_meta.lore (merchandise_lore);
					merchandise.setItemMeta (merchandise_meta);

					gui.setItem (i, merchandise);
				}
			} else {
				gui.setItem (i, setItemData.getNoDataGlassItem ());
			}
			i++;
		}
		gui.setItem (45, setItemData.getPrevItem ());
		gui.setItem (46, setItemData.getReloadItem ());
		gui.setItem (47, setItemData.getNextItem ());
		gui.setItem (48, setItemData.getLeftoversItem ());
		gui.setItem (49, setItemData.getStoreItem ());
		gui.setItem (50, setItemData.getBuyHistoryItem ());
		gui.setItem (51, setItemData.getSellHistoryItem ());
		gui.setItem (52, setItemData.getTerminalItem ());
		gui.setItem (53, setItemData.getCloseItem ());
		return gui;
	}
}
