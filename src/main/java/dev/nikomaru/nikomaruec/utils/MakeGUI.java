package dev.nikomaru.nikomaruec.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MakeGUI {
    public Inventory getGui(Player p, int size, String name, int r, int g, int b) {

        return Bukkit.createInventory(p, size, Component.text(name, TextColor.color(r, g, b)));
    }
}
