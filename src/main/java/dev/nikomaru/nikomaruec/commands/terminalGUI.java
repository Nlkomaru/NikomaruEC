package dev.nikomaru.nikomaruec.commands;

import dev.nikomaru.nikomaruec.gui.ec.terminalChestGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class terminalGUI implements CommandExecutor {
	
	//いろいろな所にアクセスできるGUIを表示するコマンドの予定
	@Override
	
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command,
		@NotNull String label,
		String[] args) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			terminalChestGUI terminal = new terminalChestGUI();
			p.openInventory(terminal.Terminal(p));
			
		}
		
		return true;
		
	}
	
}