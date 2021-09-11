package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.commands.EasySell;
import dev.nikomaru.nikomaruec.commands.TerminalGUI;
import dev.nikomaru.nikomaruec.events.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NikomaruEC extends JavaPlugin {

    private static NikomaruEC plugin;

    public static NikomaruEC getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!VaultAPI.setupEconomy()) {
            System.out.println("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        plugin = this;
        Objects.requireNonNull(getCommand("ne")).setExecutor(new TerminalGUI());
        Objects.requireNonNull(getCommand("nes")).setExecutor(new EasySell());
        getServer().getPluginManager().registerEvents(new TerminalClickEvent(), this);
        getServer().getPluginManager().registerEvents(new BuyClickEvent(), this);
        getServer().getPluginManager().registerEvents(new SellClickEvent(), this);
        getServer().getPluginManager().registerEvents(new SellCloseEvent(), this);
        getServer().getPluginManager().registerEvents(new NowStockClickEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

