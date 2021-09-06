package dev.nikomaru.nikomaruec.utils.deskription;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.events.sellClickEvent;
import dev.nikomaru.nikomaruec.gui.ec.buyChestGUI;
import dev.nikomaru.nikomaruec.utils.makeList;
import java.time.ZonedDateTime;
import java.util.Calendar;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class ConvPromptDescription extends StringPrompt {
	
	NikomaruEC plugin;
	
	@Override
	public Prompt acceptInput(ConversationContext con, String description) {
		
		Player p = (Player) con.getForWhom();
		
		if (p.getUniqueId() == sellClickEvent.getdata().get(1)) {
			
			sellClickEvent.getdata().add(description);
			
			ZonedDateTime nowTime = ZonedDateTime.now();
			ZonedDateTime limitTime = nowTime.plusDays(7);
			sellClickEvent.getdata().add(limitTime);
			
			System.out.println(sellClickEvent.getdata());
			makeList.getStocks().add(sellClickEvent.getdata());
			
			System.out.println(makeList.getStocks());
			// {itemStack} {player uuid} {price} {description} {time}
			con.getForWhom().sendRawMessage("説明は、「" + description + "」で処理しました");
			con.getForWhom().sendRawMessage("出品が完了しました");
		} else {
			con.getForWhom().sendRawMessage("処理が実行できませんでした 時間をおいてから再実行してください");
		}
		return null;
	}
	
	@Override
	public String getPromptText(ConversationContext context) {
		
		return "説明を入力してください>>>";
	}
}
