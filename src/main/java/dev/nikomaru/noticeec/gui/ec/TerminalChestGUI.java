/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetTemplateItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {
    //アクセス用のgui
    public Inventory Terminal (Player player) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (player,9,makegui.getTerminalChest ());

        SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

        gui.setItem (0,setTemplateItemData.getSellItem ());
        gui.setItem (1,setTemplateItemData.getBuyItem ());
        gui.setItem (8,setTemplateItemData.getCloseItem ());
        return gui;
    }
}
