package dev.nikomaru.nikomaruec.utils.conversation;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.files.Config;
import dev.nikomaru.nikomaruec.utils.ChangeItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class ConvPromptPrice extends StringPrompt {
	
	final String nurture_num = "^[0-9]{1,18}$";
	final Pattern p1 = Pattern.compile (nurture_num);
	
	@Override
	public Prompt acceptInput (@NotNull ConversationContext con,String price) {
		if (p1.matcher (price).matches ()) {
			Config config = new Config (NikomaruEC.getPlugin ());
			long pr = Long.parseLong (price);
			if (config.getMinPrice () <= pr && pr <= config.getMaxPrice ()) {
				
				
				Player p = (Player) con.getForWhom ();
				
				StockDataList.addData (p.getUniqueId (),pr);
				ConversationFactory cf = new ConversationFactory (NikomaruEC.getPlugin ());
				Conversation conv2 = cf.withFirstPrompt (new ConvPromptDescription ()).withLocalEcho (true)
						.buildConversation ((p));
				conv2.begin ();
				
				new BukkitRunnable () {
					
					@Override
					public void run () {
						conv2.abandon ();
						if (StockDataList.getData ().get (p.getUniqueId ()).size () <= 3) {
							p.sendMessage (ChatColor.YELLOW + "入力がないため処理を中断しました 説明をなしで出品する場合はコマンドから出品してください");
							p.getInventory ()
									.addItem (ChangeItemData.decode (StockDataList.getData ().get (p.getUniqueId ()).get (0).toString ()));
							
						}
					}
				}.runTaskLater (NikomaruEC.getPlugin (),20 * 12);
				
				con.getForWhom ().sendRawMessage (ChatColor.GREEN + "金額は" + price + "で処理しました");
			}
			else {
				con.getForWhom ().sendRawMessage (ChatColor.YELLOW + "正しく入力されませんでした" + "\n" + String.format ("金額の範囲は%dから%dまでに制限されています",config.getMinPrice (),config.getMaxPrice ()));
			}
		}
		else {
			con.getForWhom ().sendRawMessage (ChatColor.YELLOW + "正しく入力されませんでした");
		}
		
		return null;
	}
	
	@Override
	public @NotNull String getPromptText (@NotNull ConversationContext context) {
		
		return ChatColor.AQUA + "金額を入力してください>>>";
	}
	
}
