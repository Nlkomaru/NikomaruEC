package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {

    //アクセス用のGUIを作成する処理をする予定
    public Inventory Terminal(Player p) {

        MakeGUI makegui = new MakeGUI();
        Inventory gui = Bukkit.createInventory(p,9, makegui.getTerminalChest());

        SetItemData setItemData = new SetItemData();

        gui.setItem(0, setItemData.getSellItem());
        gui.setItem(1, setItemData.getBuyItem());
        gui.setItem(8, setItemData.getCloseItem());
        return gui;
    }
}
