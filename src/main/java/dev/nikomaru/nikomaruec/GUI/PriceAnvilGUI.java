package dev.nikomaru.nikomaruec.GUI;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PriceAnvilGUI {

    public Inventory Price (Player p) {

        Inventory gui = Bukkit.createInventory (p, InventoryType.ANVIL);

//        ItemStack EnterPrice = new ItemStack (Material.NAME_TAG);
//
//        ItemMeta EnterPrice_meta = EnterPrice.getItemMeta ();
//
//        ArrayList<String> EnterPrice_lore = new ArrayList<> ();
//        EnterPrice_lore.add (ChatColor.GOLD + "金額を入力してください");
//        EnterPrice_meta.setLore (EnterPrice_lore);
//
//        gui.setItem (1, EnterPrice);

        return gui;
    }
}
