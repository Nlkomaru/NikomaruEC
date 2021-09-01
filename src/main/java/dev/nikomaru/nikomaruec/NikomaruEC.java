package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.API.VaultAPI;
import dev.nikomaru.nikomaruec.Commands.GUI;
import dev.nikomaru.nikomaruec.events.ClickEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class NikomaruEC extends JavaPlugin {

    @Override public void onEnable () {
        // Plugin startup logic
        if (!VaultAPI.setupEconomy()) {
            System.out.println("No economy plugin found. Disabling Vault");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand ("ne").setExecutor (new GUI ());
        getServer().getPluginManager().registerEvents (new ClickEvent (),this);
    }

    @Override public void onDisable () {
        // Plugin shutdown logic
    }
}
