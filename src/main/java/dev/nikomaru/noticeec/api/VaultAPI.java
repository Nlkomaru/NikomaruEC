/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.api;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

import static org.bukkit.Bukkit.getServer;

public class VaultAPI {
    private static @Nullable Economy econ = null;

    public static boolean setupEconomy () {
        if (getServer ().getPluginManager ().getPlugin ("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer ().getServicesManager ().getRegistration (Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider ();
        return true;
    }

    public static @Nullable Economy getEconomy () {

        return econ;
    }
}
