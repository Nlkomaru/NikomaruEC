package dev.nikomaru.nikomaruec.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class TerminalChestGUI {
    //アクセス用のGUIを作成する処理をする予定
    public Inventory Terminal (Player p) {
        Inventory gui = Bukkit.createInventory (p, 9, ChatColor.AQUA + "Custom GUI");

        //Menu Options(Items)

        ItemStack buy = new ItemStack (Material.EMERALD); //Kills the player

        ItemStack sell = new ItemStack (Material.GOLD_INGOT); //Fills the hunger bar

        ItemStack close = new ItemStack (Material.BARRIER); //Gives the player a weapon

        //Edit the items

        ItemMeta buy_meta = buy.getItemMeta ();
        ArrayList<String> buy_lore = new ArrayList<> ();
        buy_lore.add (ChatColor.BLUE + "物品購入用");
        buy.setLore (buy_lore);

        ItemMeta sell_meta = sell.getItemMeta ();
        ArrayList<String> sell_lore = new ArrayList<> ();
        sell_lore.add (ChatColor.RED + "物品販売用");
        sell_meta.setLore (sell_lore);

        ItemMeta close_meta = close.getItemMeta ();
        ArrayList<String> close_lore = new ArrayList<> ();
        close_lore.add (ChatColor.GOLD + "閉じる");
        close_meta.setLore (close_lore);

        gui.setItem (0, sell);
        gui.setItem (1, buy);
        gui.setItem (8, close);
        return gui;
    }
}
