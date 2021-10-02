/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.commands;

import dev.nikomaru.noticeec.gui.ec.TerminalChestGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TerminalGUI implements CommandExecutor {
	
	//いろいろな所にアクセスできるGUIを表示するコマンドの予定
	@Override
	public boolean onCommand (
			@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,
			String[] args
	) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			
			
			TerminalChestGUI terminal = new TerminalChestGUI ();
			p.openInventory (terminal.Terminal (p));
			
		}
		
		return true;
		
	}
	
}