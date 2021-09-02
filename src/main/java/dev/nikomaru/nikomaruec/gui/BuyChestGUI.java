package dev.nikomaru.nikomaruec.gui;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BuyChestGUI {
    //購入用のGUIを作成する処理をする予定
    public Inventory Buy (Player p) {

        Inventory gui = Bukkit.createInventory (p, 54, ChatColor.AQUA + "Buy GUI");

        //Menu Options(Items)

        ItemStack suicide = new ItemStack (Material.TNT); //Kills the player

        ItemStack feed = new ItemStack (Material.BREAD); //Fills the hunger bar

        ItemStack sword = new ItemStack (Material.DIAMOND_SWORD); //Gives the player a weapon

        //Edit the items

        ItemMeta suicide_meta = suicide.getItemMeta ();
        ArrayList<String> suicide_lore = new ArrayList<> ();
        suicide_lore.add (ChatColor.GOLD + "Kill yourself. ;)");
        suicide_meta.setLore (suicide_lore);

        ItemMeta feed_meta = feed.getItemMeta ();
        ArrayList<String> feed_lore = new ArrayList<> ();
        feed_lore.add (ChatColor.GOLD + "Hunger no more.");
        feed_meta.setLore (feed_lore);

        ItemMeta sword_meta = sword.getItemMeta ();
        ArrayList<String> sword_lore = new ArrayList<> ();
        sword_lore.add (ChatColor.GOLD + "Get a sword.");
        sword_meta.setLore (sword_lore);

        ItemStack[] menu_items = { suicide, feed, sword };

        gui.setContents (menu_items);

        return gui;
    }

}
