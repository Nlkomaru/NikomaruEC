package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.VaultAPI;
import dev.nikomaru.nikomaruec.commands.TerminalGUI;
import dev.nikomaru.nikomaruec.events.BuyClickEvent;
import dev.nikomaru.nikomaruec.events.SellClickEvent;
import dev.nikomaru.nikomaruec.events.SellCloseEvent;
import dev.nikomaru.nikomaruec.events.TerminalClickEvent;
import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

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
				getServer().getPluginManager().registerEvents(new TerminalClickEvent(), this);
				getServer().getPluginManager().registerEvents(new BuyClickEvent(), this);
				getServer().getPluginManager().registerEvents(new SellClickEvent(), this);
				getServer().getPluginManager().registerEvents(new SellCloseEvent(), this);

		}

		@Override
		public void onDisable() {
				// Plugin shutdown logic
		}
}

