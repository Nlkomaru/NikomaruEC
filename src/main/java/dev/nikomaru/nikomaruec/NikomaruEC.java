package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.commands.EasySell;
import dev.nikomaru.nikomaruec.commands.TerminalGUI;
import dev.nikomaru.nikomaruec.events.*;
import dev.nikomaru.nikomaruec.files.stocks.ReadStockData;
import dev.nikomaru.nikomaruec.files.stocks.WriteStockData;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public final class NikomaruEC extends JavaPlugin {


    public static List<List<Object>> stocks = new ArrayList<>();
    private static NikomaruEC plugin;

    public static NikomaruEC getPlugin() {
        return plugin;
    }

    public static @NotNull List<List<Object>> getStocks() {
        return stocks;
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

        stocks = ReadStockData.readData();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WriteStockData.saveData();
    }
}

