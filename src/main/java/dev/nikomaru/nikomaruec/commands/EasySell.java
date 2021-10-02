package dev.nikomaru.nikomaruec.commands;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.files.Config;
import dev.nikomaru.nikomaruec.utils.ChangeItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EasySell implements CommandExecutor {
	//コマンドから簡単に出品できる処理をするコマンド予定
	//フォーマット /nes [金額] [説明]
	
	static List<Object> easySellData;
	final String nurture_num = "^[0-9]{1,18}$";
	final Pattern p1 = Pattern.compile (nurture_num);
	
	@Override
	public boolean onCommand (
			@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,
			String @NotNull [] args
	) {
		
		if (sender instanceof Player) {
			Player p = (Player) sender;
			easySellData = new ArrayList<> ();
			if (p.getInventory ().getItemInMainHand ().getType () != Material.AIR) {
				if (args.length == 1 || args.length == 2) {
					if (p1.matcher (args[0]).matches ()) {
						// {itemStack} {player uuid} {price} {description} {time}
						long price = Long.parseLong (args[0]);
						Config config = new Config (NikomaruEC.getPlugin ());
						if (config.getMinPrice () <= price && price <= config.getMaxPrice ()) {
							
							easySellData.add (ChangeItemData.encode (p.getInventory ().getItemInMainHand ()));
							easySellData.add (p.getUniqueId ());
							easySellData.add (price);
							
							if (args.length == 1) {
								easySellData.add ("説明はありません");
								
							}
							else {
								easySellData.add (args[1]);
							}
							
							ZonedDateTime nowTime = ZonedDateTime.now ();
							ZonedDateTime limitTime = nowTime.plusDays (config.getAddDays ()).plusHours (config.getAddHours ());
							easySellData.add (limitTime);
							p.sendMessage (ChatColor.GREEN + String.format ("%s円で、説明は「%s」で処理しました",easySellData.get (2).toString (),easySellData.get (3).toString ()));
							p.getInventory ().setItemInMainHand (new ItemStack (Material.AIR));
							
							StockDataList.addStocks (easySellData);
						}
					}
					else {
						p.sendMessage (ChatColor.YELLOW + "金額は自然数を入力してください");
					}
				}
				else {
					p.sendMessage (ChatColor.YELLOW + "コマンドの長さが不適切です");
				}
			}
			else {
				p.sendMessage (ChatColor.YELLOW + "アイテムをメインハンドに持ってください");
			}
			
		}
		
		return true;
		
	}
}
