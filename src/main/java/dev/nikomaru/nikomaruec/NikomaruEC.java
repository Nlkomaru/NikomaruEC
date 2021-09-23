package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.commands.EasySell;
import dev.nikomaru.nikomaruec.commands.TerminalGUI;
import dev.nikomaru.nikomaruec.events.*;
import dev.nikomaru.nikomaruec.files.returnStocks.WriteReturnStockData;
import dev.nikomaru.nikomaruec.files.stocks.ReadStockData;
import dev.nikomaru.nikomaruec.files.stocks.WriteStockData;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public final class NikomaruEC extends JavaPlugin {
	
	
	public static List<List<Object>> stocks = new ArrayList<> ();
	public static HashMap<UUID,List<List<Object>>> returnStocks = new HashMap<> ();
	private static NikomaruEC plugin;
	
	public static NikomaruEC getPlugin () {
		return plugin;
	}
	
	public static @NotNull List<List<Object>> getStocks () {
		return stocks;
	}
	
	public static @NotNull HashMap<UUID,List<List<Object>>> getReturnStocks () {
        return returnStocks;
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
        getServer ().getPluginManager ().registerEvents (new NowStockClickEvent (),this);
        getServer ().getPluginManager ().registerEvents (new BuyAcceptClickEvent (),this);

        stocks = ReadStockData.readData();


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
	    WriteStockData writeStockData = new WriteStockData ();
        writeStockData.saveData ();
	    WriteReturnStockData writeReturnStockData = new WriteReturnStockData ();
        writeReturnStockData.saveData();
    }
}

