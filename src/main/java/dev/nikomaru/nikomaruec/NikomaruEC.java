package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.commands.EasySell;
import dev.nikomaru.nikomaruec.commands.TerminalGUI;
import dev.nikomaru.nikomaruec.events.*;
import dev.nikomaru.nikomaruec.files.returnStocks.ReadReturnStockData;
import dev.nikomaru.nikomaruec.files.returnStocks.WriteReturnStockData;
import dev.nikomaru.nikomaruec.files.stocks.ReadStockData;
import dev.nikomaru.nikomaruec.files.stocks.WriteStockData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NikomaruEC extends JavaPlugin {
	
	
	private static NikomaruEC plugin;
	
	public static NikomaruEC getPlugin () {
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
	    Objects.requireNonNull (getCommand ("ne")).setExecutor (new TerminalGUI ());
	    Objects.requireNonNull (getCommand ("nes")).setExecutor (new EasySell ());
	    getServer ().getPluginManager ().registerEvents (new TerminalClickEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new BuyClickEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new SellClickEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new SellCloseEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new NowStockClickEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new BuyAcceptClickEvent (),this);
	    getServer ().getPluginManager ().registerEvents (new ReturnedClickEvent (),this);
	
	    StockDataList.setStocks (ReadStockData.readData ());
	    StockDataList.setReturnStocks (ReadReturnStockData.readData ());
	
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        WriteStockData.saveData ();
        WriteReturnStockData.saveData();
    }
}

