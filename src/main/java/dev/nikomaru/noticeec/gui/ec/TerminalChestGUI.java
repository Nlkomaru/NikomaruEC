/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.gui.ec;

import dev.nikomaru.noticeec.utils.MakeGUI;
import dev.nikomaru.noticeec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {

    //アクセス用のGUIを作成する処理をする予定
    public Inventory Terminal (Player p) {

        MakeGUI makegui = new MakeGUI ();
        Inventory gui = Bukkit.createInventory (p,9,makegui.getTerminalChest ());

        SetItemData setItemData = new SetItemData ();

        gui.setItem (0,setItemData.getSellItem ());
        gui.setItem (1,setItemData.getBuyItem ());
        gui.setItem (8,setItemData.getCloseItem ());
        return gui;
    }
}
