package dev.nikomaru.nikomaruec.Commands;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

public class TerminalGUI implements CommandExecutor {

    @Override

    public boolean onCommand (@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory (player, 9, ChatColor.AQUA + "Custom GUI");

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


            gui.setItem (0,sell);
            gui.setItem (1,buy);
            gui.setItem (8,close);


            player.openInventory (gui);






        }

        return true;



    }



}