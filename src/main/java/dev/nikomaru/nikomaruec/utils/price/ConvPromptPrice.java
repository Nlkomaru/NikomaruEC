package dev.nikomaru.nikomaruec.utils.price;

import dev.nikomaru.nikomaruec.utils.addStockData;
import java.util.regex.Pattern;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;

public class ConvPromptPrice extends StringPrompt {
	
	String nurture_num = "^[1-9][0-9]*$";
	Pattern p1 = Pattern.compile(nurture_num);
	
	
	@Override
	public Prompt acceptInput(ConversationContext con, String price) {
		if (p1.matcher(price).matches()) {
			
			int pr = Integer.parseInt(price);
			
			addStockData setPrice = new addStockData();
			setPrice.addData(pr);
			setPrice.addCounter(1);
			
			Player p = (Player) con.getForWhom();
			
			con.getForWhom().sendRawMessage("金額は" + price + "で処理しました");
		} else {
			con.getForWhom().sendRawMessage("正しく入力されませんでした");
		}
		return null;
	}
	
	@Override
	public String getPromptText(ConversationContext context) {
		
		return "金額を入力してください";
	}
	
	
}
