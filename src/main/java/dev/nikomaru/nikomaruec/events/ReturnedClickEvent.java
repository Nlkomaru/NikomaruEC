package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.NowStockChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.ReturnedChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.TerminalChestGUI;
import dev.nikomaru.nikomaruec.utils.*;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static dev.nikomaru.nikomaruec.utils.StockDataList.getNowStockPage;

public class ReturnedClickEvent implements Listener {
	
	@EventHandler
	public void clickEvent(InventoryClickEvent e){
		MakeGUI makegui = new MakeGUI();
		if(e.getView ().title ().equals (makegui.getReturnedChest ())) {
			if(e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {
					Player p = (Player) e.getWhoClicked ();
					UUID uuid = p.getUniqueId ();
					int clickedSlot = e.getSlot ();
					int pages = StockDataList.getReturnPage ().get (uuid);
					int returnedNum = NikomaruEC.getReturnStocks ().get (uuid).size ();
					int maxPage = (int) Math.ceil ((double) returnedNum / 45);
					int num = clickedSlot + (pages - 1) * 45;
					if ((0 <= clickedSlot && clickedSlot <= 44) && num < returnedNum) {
						if (p.getInventory ().firstEmpty () == - 1) {
							SetItemData setItemData = new SetItemData ();
							e.getClickedInventory ().setItem (clickedSlot,setItemData.getNoticeNoEmptyItem ());
							new BukkitRunnable () {
								@Override
								public void run () {
									GetItemMeta getItemMeta = new GetItemMeta ();
									e.getClickedInventory ().setItem (clickedSlot,getItemMeta.setItemMeta (NikomaruEC.getReturnStocks ().get (uuid).get (num)));
								}
							}.runTaskLater (NikomaruEC.getPlugin (),20 * 2);
						}
						else {
							p.getInventory ().addItem (ChangeItemData.decode(NikomaruEC.getReturnStocks ().get (uuid).get (num).get (0).toString ()));
							NikomaruEC.getReturnStocks ().get (uuid).remove (num);
							ReturnedChestGUI returnedChestGUI = new ReturnedChestGUI ();
							p.openInventory (returnedChestGUI.returned (p,pages));
						}
					}
					else if (clickedSlot >= 45 && clickedSlot <= 47) {
						changePages (e,p,uuid,pages,clickedSlot,maxPage);
					}
					else if (clickedSlot == 48) {
						//販売場
						BuyChestGUI buyChestGUI = new BuyChestGUI ();
						p.openInventory (buyChestGUI.Buy (p,1));
						getNowStockPage ().put (uuid,1);
					}
					else if (clickedSlot == 49) {
						//自分の販売中の在庫
						NowStockChestGUI nowStock = new NowStockChestGUI ();
						p.openInventory (nowStock.nowPlayerStock (p,1));
						getNowStockPage ().put (p.getUniqueId (),1);
					}
					else if (clickedSlot == 50) {
						//購入履歴
					}
					else if (clickedSlot == 51) {
						//販売履歴
					}
					else if (clickedSlot == 52) {
						//ターミナルを開く
						TerminalChestGUI terminal = new TerminalChestGUI ();
						p.openInventory (terminal.Terminal (p));
					}
					else if (clickedSlot == 53) {
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
		
		StockDataList.getReturnPage ().put (playerUUID,pages + change);
		ReturnedChestGUI returned = new ReturnedChestGUI ();
		p.openInventory (returned.returned (p,pages + change));
		e.setCancelled (true);
	}
}
