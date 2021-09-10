package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.conversation.ConvPromptPrice;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class SellClickEvent implements Listener {

	public static List<Object> data;

	public static List<Object> getData () {
		return data;
	}

	//販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
	@EventHandler public void clickEvent (InventoryClickEvent e) {

		Player pl = (Player) e.getWhoClicked ();
		if (e.getView ().title ().equals (Component.text ("物品販売所", TextColor.color (251, 107, 255)))) {
			if (e.getClickedInventory () != null) {
				InventoryType inv = e.getClickedInventory ().getType ();
				if (inv == InventoryType.CHEST) {

					int s = e.getSlot ();
					if (0 <= s && s <= 2 || 4 <= s && s <= 8) {

						if (s == 8) {
							pl.closeInventory ();

						} else if (s == 7) {
							ItemStack item;
							item = Objects.requireNonNull (e.getClickedInventory ()).getItem (3);
							e.getClickedInventory ().clear (3);
							if (item != null) {
								pl.closeInventory ();
								data = new ArrayList<> ();
								data.add (item);
								data.add (pl.getUniqueId ());

								ConversationFactory cf = new ConversationFactory (NikomaruEC.getPlugin ());
								Conversation conv1 = cf.withFirstPrompt (new ConvPromptPrice ()).withLocalEcho (true)
										.buildConversation ((pl));
								conv1.begin ();

								new BukkitRunnable () {

									@Override public void run () {
										conv1.abandon ();
										if (data.size () <= 2) {
											pl.sendMessage ("入力がないため処理を中断しました");
											pl.getInventory ().addItem ((ItemStack) SellClickEvent.getData ().get (0));
										}
									}
								}.runTaskLater (NikomaruEC.getPlugin (), 20 * 7);

							}
						}
						e.setCancelled (true);
					}
				}
			}
		}
	}

}