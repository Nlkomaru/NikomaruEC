package dev.nikomaru.nikomaruec.utils.deskription;

import dev.nikomaru.nikomaruec.utils.addStockData;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;

public class ConvPromptDescription extends StringPrompt {
	
	@Override
	public Prompt acceptInput(ConversationContext con, String description) {
		
		addStockData setDescription = new addStockData();
		setDescription.addData(description);
		setDescription.addCounter(2);
		return null;
	}
	
	@Override
	public String getPromptText(ConversationContext context) {
		
		return "説明を入力してください";
	}
}
