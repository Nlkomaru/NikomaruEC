/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.utils.ChangeItemData;
import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.StockDataList;
import dev.nikomaru.noticeec.utils.conversation.ConvPromptPrice;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Objects;

public class SellClickEvent implements Listener {
	
	
	//販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
	@EventHandler
	public void clickEvent (InventoryClickEvent e) {
		
		Player pl = (Player) e.getWhoClicked ();
		MakeGUI makegui = new MakeGUI ();
		if (e.getView ().title ().equals (makegui.getSellChest ())) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {
					
					int s = e.getSlot ();
					if (0 <= s && s <= 2 || 4 <= s && s <= 8) {
						
						if (s == 8) {
							pl.closeInventory ();
							
						}
						else if (s == 7) {
							ItemStack item;
							item = Objects.requireNonNull (e.getClickedInventory ()).getItem (3);
							e.getClickedInventory ().clear (3);
							if (item != null) {
								pl.closeInventory ();
								StockDataList.putNewData (pl.getUniqueId ());
								StockDataList.addData (pl.getUniqueId (),ChangeItemData.encode (item));
								StockDataList.addData (pl.getUniqueId (),pl.getUniqueId ());
								
								ConversationFactory cf = new ConversationFactory (NoticeEC.getPlugin ());
								Conversation conv1 = cf.withFirstPrompt (new ConvPromptPrice ()).withLocalEcho (true)
										.buildConversation ((pl));
								conv1.begin ();
								
								new BukkitRunnable () {
									
									@Override
									public void run () {
										conv1.abandon ();
										if (StockDataList.getData ().get (pl.getUniqueId ()).size () <= 2) {
											pl.sendMessage ("入力がないため処理を中断しました");
											pl.getInventory ().addItem (ChangeItemData.decode (StockDataList.getData ().get (pl.getUniqueId ()).get (0).toString ()));
										}
									}
								}.runTaskLater (NoticeEC.getPlugin (),20 * 7);
								
							}
						}
						e.setCancelled (true);
					}
				}
			}
		}
	}
	
}