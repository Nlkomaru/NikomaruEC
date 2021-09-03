package dev.nikomaru.nikomaruec.gui.ec;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class BuyChestGUI {
    //購入用のGUIを作成する処理をする予定
    public Inventory Buy (Player p , int pages) {

        Inventory gui = Bukkit.createInventory (p, 54, ChatColor.AQUA + "Buy GUI");
        int i = 0;
        int num = (pages-1)*45;
        while( i > num) {
            //ifで日付チェック
            //アイテムをgui.setItem(i,stock(i))でセット
            //whileなのでiを足す
        }

        return gui;
    }

}
