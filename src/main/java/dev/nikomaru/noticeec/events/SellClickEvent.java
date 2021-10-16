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

public class SellClickEvent implements Listener {
    //販売用のアイテムがクリックされたら販売用GUIに飛ぶ処理をする予定
    @EventHandler
    public void clickEvent (InventoryClickEvent e) {

        Player player = (Player) e.getWhoClicked ();
        MakeGUI makegui = new MakeGUI ();

        //タイトルがあっているか
        if (!(e.getView ().title ().equals (makegui.getSellChest ()) && e.getClickedInventory () != null)) {
            return;
        }
        //インベントリタイプがチェストか
        InventoryType inv = e.getClickedInventory ().getType ();
        if (inv != InventoryType.CHEST) {
            return;
        }

        int s = e.getSlot ();
        if (!(0 <= s && s <= 2 || 4 <= s && s <= 8)) {
            return;
        }

        if (s == 8) {
            player.closeInventory ();
        } else if (s == 7) {
            if (e.getClickedInventory ().getItem (3) == null) {
                return;
            }

            ItemStack item = e.getClickedInventory ().getItem (3);
            e.getClickedInventory ().clear (3);
            player.closeInventory ();
            StockDataList.putNewData (player.getUniqueId ());
            StockDataList.addData (player.getUniqueId (),ChangeItemData.encode (item));
            StockDataList.addData (player.getUniqueId (),player.getUniqueId ());

            ConversationFactory cf = new ConversationFactory (NoticeEC.getPlugin ());
            Conversation conv1 = cf.withFirstPrompt (new ConvPromptPrice ()).withLocalEcho (true)
                    .buildConversation ((player));
            conv1.begin ();

            new BukkitRunnable () {
                @Override
                public void run () {
                    conv1.abandon ();
                    if (StockDataList.getData ().get (player.getUniqueId ()).size () <= 2) {
                        player.sendMessage ("入力がないため処理を中断しました");
                        player.getWorld ().dropItem (player.getLocation (),ChangeItemData.decode (
                                StockDataList.getData ().get (player.getUniqueId ()).get (0).toString ()));
                    }
                }
            }.runTaskLater (NoticeEC.getPlugin (),20 * 7);
        }
        e.setCancelled (true);
    }
}
