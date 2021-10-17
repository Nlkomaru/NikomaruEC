/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.commands;

import dev.nikomaru.noticeec.gui.ec.TerminalChestGUI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TerminalGUI implements CommandExecutor {
    //いろいろな所にアクセスできるGUIを表示するコマンド
    @Override
    public boolean onCommand (@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;

            TerminalChestGUI terminal = new TerminalChestGUI ();
            p.openInventory (terminal.Terminal (p));
        }

        return true;
    }
}