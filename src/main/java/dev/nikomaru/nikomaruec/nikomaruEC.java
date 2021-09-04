package dev.nikomaru.nikomaruec;

import dev.nikomaru.nikomaruec.api.vaultAPI;
import dev.nikomaru.nikomaruec.commands.terminalGUI;
import dev.nikomaru.nikomaruec.events.chat.priceChatEvent;
import dev.nikomaru.nikomaruec.events.click.buyClickEvent;
import dev.nikomaru.nikomaruec.events.click.sellClickEvent;
import dev.nikomaru.nikomaruec.events.click.terminalClickEvent;
import java.util.Objects;
import org.bukkit.plugin.java.JavaPlugin;

public final class nikomaruEC extends JavaPlugin {

    @Override public void onEnable () {
        // Plugin startup logic
        if (!vaultAPI.setupEconomy ()) {
            System.out.println ("No economy plugin found. Disabling Vault");
            getServer ().getPluginManager ().disablePlugin (this);
            return;
        }

        Objects.requireNonNull (getCommand ("ne")).setExecutor (new terminalGUI ());
        getServer ().getPluginManager ().registerEvents (new terminalClickEvent (), this);
        getServer ().getPluginManager ().registerEvents (new buyClickEvent (), this);
        getServer ().getPluginManager ().registerEvents (new sellClickEvent (), this);
        getServer ().getPluginManager ().registerEvents (new priceChatEvent (), this);
    }

    @Override public void onDisable () {
        // Plugin shutdown logic
    }
}
