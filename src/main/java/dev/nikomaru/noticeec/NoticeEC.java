/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec;

import dev.nikomaru.noticeec.api.VaultAPI;
import dev.nikomaru.noticeec.commands.EasySell;
import dev.nikomaru.noticeec.commands.TerminalGUI;
import dev.nikomaru.noticeec.events.*;
import dev.nikomaru.noticeec.files.returnStocks.ReadReturnStockData;
import dev.nikomaru.noticeec.files.returnStocks.WriteReturnStockData;
import dev.nikomaru.noticeec.files.stocks.ReadStockData;
import dev.nikomaru.noticeec.files.stocks.WriteStockData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class NoticeEC extends JavaPlugin {
	
	
	private static NoticeEC plugin;
	
	public static NoticeEC getPlugin () {
		return plugin;
	}
	
	
	@Override
	public void onEnable () {
		// Plugin startup logic
		if (! VaultAPI.setupEconomy ()) {
			System.out.println ("No economy plugin found. Disabling Vault");
			getServer ().getPluginManager ().disablePlugin (this);
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
	public void onDisable () {
		// Plugin shutdown logic
		WriteStockData.saveData ();
		WriteReturnStockData.saveData ();
	}
}

