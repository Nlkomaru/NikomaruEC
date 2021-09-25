package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.ChangeItemData;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.conversation.ConvPromptPrice;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class SellClickEvent implements Listener {

    public static List<Object> data;
    static @NotNull HashMap<UUID, List<Object>> sellData = new HashMap<>();

    public static @NotNull HashMap<UUID, List<Object>> getData() {
        return sellData;
    }

    //販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
    @EventHandler
    public void clickEvent(@NotNull InventoryClickEvent e) {

        Player pl = (Player) e.getWhoClicked();
        MakeGUI makegui = new MakeGUI();
        if (e.getView().title().equals(makegui.getSellChest())) {
            if (e.getClickedInventory() != null) {
                InventoryType inv = e.getClickedInventory().getType();
                if (inv == InventoryType.CHEST) {

                    int s = e.getSlot();
                    if (0 <= s && s <= 2 || 4 <= s && s <= 8) {

                        if (s == 8) {
                            pl.closeInventory();

                        } else if (s == 7) {
                            ItemStack item;
                            item = Objects.requireNonNull(e.getClickedInventory()).getItem(3);
                            e.getClickedInventory().clear(3);
                            if (item != null) {
                                pl.closeInventory();
                                data = new ArrayList<>();
                                sellData.put (pl.getUniqueId (),data);
                                sellData.get (pl.getUniqueId ()).add (ChangeItemData.encode (item));
                                sellData.get (pl.getUniqueId ()).add (pl.getUniqueId ());

                                ConversationFactory cf = new ConversationFactory(NikomaruEC.getPlugin());
                                Conversation conv1 = cf.withFirstPrompt(new ConvPromptPrice()).withLocalEcho(true)
                                        .buildConversation((pl));
                                conv1.begin();

                                new BukkitRunnable() {

                                    @Override
                                    public void run() {
                                        conv1.abandon();
                                        if (sellData.get(pl.getUniqueId()).size() <= 2) {
                                            pl.sendMessage ("入力がないため処理を中断しました");
                                            pl.getInventory ().addItem (ChangeItemData.decode (SellClickEvent.getData ().get (pl.getUniqueId ()).get (0).toString ()));
                                        }
                                    }
                                }.runTaskLater(NikomaruEC.getPlugin(), 20 * 7);

                            }
                        }
                        e.setCancelled(true);
                    }
                }
            }
        }
    }

}