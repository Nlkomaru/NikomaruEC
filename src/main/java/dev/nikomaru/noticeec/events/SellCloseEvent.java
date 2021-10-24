/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
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
        InventoryType inv = e.getInventory ().getType ();
        //インベントリタイプがチェストか
        if (inv != InventoryType.CHEST) {
            return;
        }
        //アイテムを入れたままインベントリを閉じたときの処理
        if (e.getInventory ().getItem (3) != null) {

            if (player.getInventory ().firstEmpty () == -1) {
                player.getWorld ()
                        .dropItem (player.getLocation (),Objects.requireNonNull (e.getInventory ().getItem (3)));
            } else {
                player.getInventory ().addItem (Objects.requireNonNull (e.getInventory ().getItem (3)));
            }
        }
    }
}
