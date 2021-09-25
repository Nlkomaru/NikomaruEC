package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.NowStockChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.ReturnedChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.TerminalChestGUI;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static dev.nikomaru.nikomaruec.utils.StockDataList.getNowBuyPage;
import static dev.nikomaru.nikomaruec.utils.StockDataList.getReturnPage;


public class NowStockClickEvent implements Listener {

    @EventHandler
    public void clickEvent(@NotNull InventoryClickEvent e) {
	
	    Player p = (Player) e.getWhoClicked();
	    MakeGUI makegui = new MakeGUI();
	    if (e.getView().title().equals(makegui.getNowStockChest())) {
		    if (e.getClickedInventory() != null) {
			    InventoryType inv = e.getClickedInventory().getType();
			    if (inv == InventoryType.CHEST) {
				    UUID playerUUID = p.getUniqueId();
				    int pages = StockDataList.getNowBuyPage().get(playerUUID);
				    int i = e.getSlot();
				    int num = i + (pages - 1) * 45;
				    int maxPage = NikomaruEC.getStocks().size() / 45;
				
				    //1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる
				    if (i >= 45 && i <= 47) {
					    changePages (e,p,playerUUID,pages,i,maxPage);
				    } else if (i == 48) {
					
					    //売れなかった在庫
					    ReturnedChestGUI returned = new ReturnedChestGUI ();
					    p.openInventory (returned.returned (p,1));
					    getReturnPage ().put (p.getUniqueId (),1);
					    e.setCancelled (true);
				    } else if (i == 49) {
					    //物品購入所
					    BuyChestGUI buy = new BuyChestGUI ();
					    p.openInventory (buy.Buy (p,1));
					    getNowBuyPage ().put (p.getUniqueId (),1);
					    e.setCancelled (true);
					
				    } else if (i == 50) {
					    //購入履歴
				    } else if (i == 51) {
					    //販売履歴
				    } else if (i == 52) {
					    //ターミナルを開く
					    TerminalChestGUI terminal = new TerminalChestGUI();
					    p.openInventory(terminal.Terminal(p));
				    } else if (i == 53) {
					    //閉じる
					    p.closeInventory ();
				    }
				
				    e.setCancelled (true);
				
			    }
			
		    }
	    }
    }
	
	static void changePages (@NotNull InventoryClickEvent e,Player p,UUID playerUUID,int pages,int i,int maxPage) {
		
		int change = 0;
		if (pages > 1 && i == 45) {
			change = - 1;
			
		}
		else if (pages <= 1 && i == 47 && pages < maxPage) {
			
			change = 1;
			
		}
		
		StockDataList.getNowStockPage ().put (playerUUID,pages + change);
		NowStockChestGUI nowStock = new NowStockChestGUI ();
		p.openInventory (nowStock.nowPlayerStock (p,pages + change));
		e.setCancelled (true);
	}
}
