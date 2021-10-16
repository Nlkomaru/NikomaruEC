/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.events;

import dev.nikomaru.noticeec.utils.MakeGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;

import java.util.Objects;

public class SellCloseEvent implements Listener {
    @EventHandler
    public void InventoryCloseEvent (InventoryCloseEvent e) {
        Player player = (Player) e.getPlayer ();
        MakeGUI makegui = new MakeGUI ();

        //タイトルがあっているか
        if (!e.getView ().title ().equals (makegui.getSellChest ())) {
            return;
        }
        e.getInventory ();
        InventoryType inv = e.getInventory ().getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }
        //アイテムを入れたままインベントリを閉じたときの処理
        if (e.getInventory ().getItem (3) != null) {
            player.getWorld ().dropItem (player.getLocation (),Objects.requireNonNull (e.getInventory ().getItem (3)));
        }
    }
}
