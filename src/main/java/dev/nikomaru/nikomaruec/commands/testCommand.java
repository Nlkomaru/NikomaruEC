package dev.nikomaru.nikomaruec.commands;

import dev.nikomaru.nikomaruec.files.stocks.ReadStockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class testCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,String[] args) {

        ReadStockData.readData();

        return true;
    }

}
