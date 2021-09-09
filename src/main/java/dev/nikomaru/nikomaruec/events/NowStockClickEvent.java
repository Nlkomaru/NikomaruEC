package dev.nikomaru.nikomaruec.events;

import static dev.nikomaru.nikomaruec.utils.StockDataList.getNowBuyPage;

import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.TerminalChestGUI;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class NowStockClickEvent implements Listener {

	@EventHandler public void clickEvent (InventoryClickEvent e) {

		Player p = (Player) e.getWhoClicked ();
		if (e.getView ().title ().equals (Component.text ("販売履歴を見る", TextColor.color (255, 0, 255)))) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {
					UUID playerUUID = p.getUniqueId ();
					int pages = StockDataList.getNowBuyPage ().get (playerUUID);
					int i = e.getSlot ();
					int num = i + (pages - 1) * 45;
					int maxPage = StockDataList.getStocks ().size () / 45;

					//1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる
					if (i >= 45 && i <= 47) {
						int change = 0;
						if (pages > 1 && i == 45) {
							change = -1;
						} else if (pages <= 1 && i == 47 && pages < maxPage) {
							change = 1;
						}
						StockDataList.getNowBuyPage ().put (playerUUID, pages + change);
						BuyChestGUI buy = new BuyChestGUI ();
						p.openInventory (buy.Buy (p, pages + change));
						e.setCancelled (true);
					} else if (i == 48) {
						//物品購入所
						BuyChestGUI buy = new BuyChestGUI ();
						p.openInventory (buy.Buy (p, 1));
						getNowBuyPage ().put (p.getUniqueId (), 1);
						e.setCancelled (true);
					} else if (i == 49) {
						//売れなかった在庫

					} else if (i == 50) {
						//購入履歴
					} else if (i == 51) {
						//販売履歴
					} else if (i == 52) {
						//ターミナルを開く
						TerminalChestGUI terminal = new TerminalChestGUI ();
						p.openInventory (terminal.Terminal (p));
					} else if (i == 53) {
						//閉じる
						p.closeInventory ();
					}

					e.setCancelled (true);

				}

			}
		}
	}
}
