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

public class GUI implements CommandExecutor {

    @Override

    public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory (player, 54, ChatColor.AQUA + "Custom GUI");

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


            ItemMeta sword_meta = sword.getItemMeta ();;
            ArrayList<String> sword_lore = new ArrayList<> ();
            sword_lore.add (ChatColor.GOLD + "Get a sword.");
            sword_meta.setLore (sword_lore);


            ItemStack[] menu_items = { suicide, feed, sword };

            gui.setContents (menu_items);

            player.openInventory (gui);

        }

        return true;

    }

}