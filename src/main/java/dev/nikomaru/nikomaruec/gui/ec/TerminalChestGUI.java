package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class TerminalChestGUI {

    //アクセス用のGUIを作成する処理をする予定
    public Inventory Terminal(Player p) {

        MakeGUI makegui = new MakeGUI();
        Inventory gui = makegui.getGui(p, 9, "メニュー", 100, 149, 237);

        SetItemData setItemData = new SetItemData();

        gui.setItem(0, setItemData.getSellItem());
        gui.setItem(1, setItemData.getBuyItem());
        gui.setItem(8, setItemData.getCloseItem());
        return gui;
    }
}
