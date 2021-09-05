package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.addStockData;
import dev.nikomaru.nikomaruec.utils.deskription.ConvPromptDescription;
import dev.nikomaru.nikomaruec.utils.price.ConvPromptPrice;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bukkit.ChatColor;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class sellClickEvent implements Listener {
	
	NikomaruEC plugin;
	
	//販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
	@EventHandler
	public void clickEvent(InventoryClickEvent e) {
		
		Player pl = (Player) e.getWhoClicked();
		if (e.getView().getTitle().equalsIgnoreCase(ChatColor.LIGHT_PURPLE + "物品販売所")) {
			if (e.getClickedInventory() != null) {
				InventoryType inv = e.getClickedInventory().getType();
				if (inv == InventoryType.CHEST) {
					
					int s = e.getSlot();
					if (0 <= s && s <= 2 || 4 <= s && s <= 8) {
						
						if (s == 8) {
							pl.closeInventory();
							
						} else if (s == 7) {
							
							ItemStack item = Objects.requireNonNull(e.getClickedInventory())
								.getItem(3);
							e.getClickedInventory().clear(3);
							if (item != null) {
								pl.closeInventory();
								List<Object> data = new ArrayList<>();
								
								addStockData setItem = new addStockData();
								setItem.addData(item);
								setItem.addCounter(0);
								data.add(addStockData.getData());
								
								if (addStockData.getCounter() == 0) {
									
									System.out.println(data);
									System.out.println(data.size());
									
									ConversationFactory cf = new ConversationFactory(plugin);
									Conversation conv1 = cf.withFirstPrompt(new ConvPromptPrice())
										.withLocalEcho(true).buildConversation((pl));
									conv1.begin();
									
									data.add(addStockData.getData());
									if (addStockData.getCounter() == 1) {
										
										System.out.println(data);
										System.out.println(data.size());
										
										Conversation conv2 = cf.withFirstPrompt(
												new ConvPromptDescription()).withLocalEcho(true)
											.buildConversation((pl));
										conv2.begin();
										data.add(addStockData.getData());
										if (addStockData.getCounter() == 2) {
											
											System.out.println(data);
											System.out.println(data.size());
										}
									}
								}
							}
						}
						e.setCancelled(true);
					}
				}
			}
		}
	}
}