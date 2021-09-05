package dev.nikomaru.nikomaruec.utils.deskription;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.events.sellClickEvent;
import dev.nikomaru.nikomaruec.gui.ec.buyChestGUI;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.scheduler.BukkitRunnable;

public class ConvPromptDescription extends StringPrompt {
	
	NikomaruEC plugin;
	@Override
	public Prompt acceptInput(ConversationContext con, String description) {
		
		sellClickEvent.getdata().add(description);
		System.out.println(sellClickEvent.getdata());
		buyChestGUI.getStock().add(sellClickEvent.getdata());
		System.out.println(buyChestGUI.getStock());
		
		con.getForWhom().sendRawMessage("説明は、「" + description + "」で処理しました");
		con.getForWhom().sendRawMessage("出品が完了しました");
		
		return null;
	}
	
	@Override
	public String getPromptText(ConversationContext context) {
		
		return "説明を入力してください>>>";
	}
}
