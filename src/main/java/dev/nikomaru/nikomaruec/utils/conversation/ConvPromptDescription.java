package dev.nikomaru.nikomaruec.utils.conversation;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.files.Config;
import dev.nikomaru.nikomaruec.files.stocks.WriteStockData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public class ConvPromptDescription extends StringPrompt {
	
	@Override
	public Prompt acceptInput (@NotNull ConversationContext con,String description) {
		
		Player p = (Player) con.getForWhom ();
		
		StockDataList.addData (p.getUniqueId (),description);
		
		ZonedDateTime nowTime = ZonedDateTime.now ();
		Config config = new Config (NikomaruEC.getPlugin ());
		ZonedDateTime limitTime = nowTime.plusDays (config.getAddDays ()).plusHours (config.getAddHours ());
		StockDataList.addData (p.getUniqueId (),limitTime);
		
		StockDataList.getStocks ().add (StockDataList.getData ().get (p.getUniqueId ()));
		
		// {itemStack} {player uuid} {price} {description} {time}
		con.getForWhom ().sendRawMessage (
				ChatColor.GREEN + "説明は、「" + ChatColor.WHITE + description + ChatColor.GREEN + "」で処理しました");
		con.getForWhom ().sendRawMessage (ChatColor.DARK_GREEN + "出品が完了しました");
		
		WriteStockData.saveData ();
		return null;
	}
	
	@Override
	public @NotNull String getPromptText (@NotNull ConversationContext context) {
		
		return ChatColor.AQUA + "説明を入力してください>>>";
	}
}
