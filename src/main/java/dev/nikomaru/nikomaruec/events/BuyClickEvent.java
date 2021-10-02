package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.gui.ec.*;
import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.UUID;


public class BuyClickEvent implements Listener {
	
	//購入用のアイテムがクリックされたら購入用GUIに飛ぶ処理をする予定
	@EventHandler
	public void clickEvent (@NotNull InventoryClickEvent e) {
		
		Player p = (Player) e.getWhoClicked ();
		MakeGUI makegui = new MakeGUI ();
		if (e.getView ().title ().equals (makegui.getBuyChest ())) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {
					UUID uuid = p.getUniqueId ();
					int pages = StockDataList.getNowBuyPage ().get (uuid);
					int i = e.getSlot ();
					int num = i + (pages - 1) * 45;
					int stockNum = StockDataList.getStocks ().size ();
					int maxPage = (int) Math.ceil ((double) stockNum / 45);
					SetItemData setItemData = new SetItemData ();
					Economy eco = VaultAPI.getEconomy ();
					//1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる
					if ((0 <= i && 44 >= i) && num < StockDataList.getStocks ().size ()) {
						
						if (StockDataList.getStocks ().get (num).get (1).equals (uuid)) {
							
							e.getClickedInventory ().setItem (i,setItemData.getNoticeYoursItem ());
							
							new BukkitRunnable () {
								@Override
								public void run () {
									GetItemMeta getItemMeta = new GetItemMeta ();
									e.getClickedInventory ().setItem (i,getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
								}
							}.runTaskLater (NikomaruEC.getPlugin (),20 * 2);
							
						}
						else {
							if (Objects.requireNonNull (eco).getBalance (p.getPlayer ()) > (long) StockDataList.getStocks ().get (i).get (2)) {
								if (p.getInventory ().firstEmpty () == - 1) {
									
									e.getClickedInventory ().setItem (i,setItemData.getNoticeNoEmptyItem ());
									new BukkitRunnable () {
										@Override
										public void run () {
											GetItemMeta getItemMeta = new GetItemMeta ();
											e.getClickedInventory ().setItem (i,getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
										}
									}.runTaskLater (NikomaruEC.getPlugin (),20 * 2);
								}
								else {
									BuyAcceptChestGUI buyAcceptChestGUI = new BuyAcceptChestGUI ();
									p.openInventory (buyAcceptChestGUI.BuyAccept (p,num));
								}
							}
							else {
								e.getClickedInventory ().setItem (i,setItemData.getNoticeNoMoneyItem ());
								new BukkitRunnable () {
									@Override
									public void run () {
										GetItemMeta getItemMeta = new GetItemMeta ();
										e.getClickedInventory ().setItem (i,getItemMeta.setItemMeta (StockDataList.getStocks ().get (num)));
									}
								}.runTaskLater (NikomaruEC.getPlugin (),20 * 2);
							}
						}
					}
					else if (i >= 45 && i <= 47) {
						
						changePages (e,p,uuid,pages,i,maxPage);
						
					}
					else if (i == 48) {
						
						//売れなかった在庫
						ReturnedChestGUI returnedStock = new ReturnedChestGUI ();
						p.openInventory (returnedStock.returned (p,1));
						StockDataList.putReturnPage (p.getUniqueId (),1);
						
					}
					else if (i == 49) {
						//自分の販売中の在庫
						
						NowStockChestGUI nowStock = new NowStockChestGUI ();
						p.openInventory (nowStock.nowPlayerStock (p,1));
						StockDataList.putNowStockPage (p.getUniqueId (),1);
						
					}
					else if (i == 50) {
						
						//購入履歴
						
					}
					else if (i == 51) {
						
						//販売履歴
						
					}
					else if (i == 52) {
						
						//ターミナルを開く
						
						TerminalChestGUI terminal = new TerminalChestGUI ();
						p.openInventory (terminal.Terminal (p));
						
					}
					else if (i == 53) {
						
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
		
		StockDataList.putNowBuyPage (playerUUID,pages + change);
		BuyChestGUI buy = new BuyChestGUI ();
		p.openInventory (buy.Buy (p,pages + change));
		e.setCancelled (true);
	}
	
}