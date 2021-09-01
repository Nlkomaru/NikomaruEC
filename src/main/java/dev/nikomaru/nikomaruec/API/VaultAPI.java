package dev.nikomaru.nikomaruec.API;

import static org.bukkit.Bukkit.getServer;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;

public class VaultAPI {


    private static Economy econ = null;

    public static boolean setupEconomy () {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;

    }

    public static Economy getEconomy()
    {

        return econ;
    }
}
