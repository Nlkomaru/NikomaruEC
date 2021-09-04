package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.vaultAPI;
import dev.nikomaru.nikomaruec.commands.terminalGUI;
import dev.nikomaru.nikomaruec.events.inventry.buyClickEvent;
import dev.nikomaru.nikomaruec.events.inventry.sellClickEvent;
import dev.nikomaru.nikomaruec.events.inventry.sellCloseEvent;
import dev.nikomaru.nikomaruec.events.inventry.terminalClickEvent;
import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

public final class NikomaruEC extends JavaPlugin {
	
	@Override
	public void onEnable() {
		// Plugin startup logic
		if (!vaultAPI.setupEconomy()) {
			System.out.println("No economy plugin found. Disabling Vault");
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		Objects.requireNonNull(getCommand("ne")).setExecutor(new terminalGUI());
		getServer().getPluginManager().registerEvents(new terminalClickEvent(), this);
		getServer().getPluginManager().registerEvents(new buyClickEvent(), this);
		getServer().getPluginManager().registerEvents(new sellClickEvent(), this);
		getServer().getPluginManager().registerEvents(new sellCloseEvent(), this);
	}
	
	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
