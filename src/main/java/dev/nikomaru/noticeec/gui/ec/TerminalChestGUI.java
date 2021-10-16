/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
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
