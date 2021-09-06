package dev.nikomaru.nikomaruec.utils.price;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.events.sellClickEvent;
import dev.nikomaru.nikomaruec.utils.deskription.ConvPromptDescription;
import java.util.regex.Pattern;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ConvPromptPrice extends StringPrompt {
	
	String nurture_num = "^[1-9][0-9]*$";
	Pattern p1 = Pattern.compile(nurture_num);
	NikomaruEC plugin;
	
	
	@Override
	public Prompt acceptInput(ConversationContext con, String price) {
		if (p1.matcher(price).matches()) {
			
			long pr = Long.parseLong(price);
			
			Player p = (Player) con.getForWhom();
			
			if (p.getUniqueId() == sellClickEvent.getdata().get(1)) {
				sellClickEvent.getdata().add(pr);
				ConversationFactory cf = new ConversationFactory(plugin);
				Conversation conv2 = cf.withFirstPrompt(new ConvPromptDescription())
					.withLocalEcho(true)
					.buildConversation((p));
				conv2.begin();
				
				new BukkitRunnable() {
					
					@Override
					public void run() {
						conv2.abandon();
						if (sellClickEvent.getdata().size() <= 2) {
							p.sendMessage("入力がないため処理を中断しました 説明をなしで出品する場合はコマンドから出品してください");
						}
					}
				}.runTaskLater(NikomaruEC.getPlugin(), 20 * 15);
				
				con.getForWhom().sendRawMessage("金額は" + price + "で処理しました");
			} else {
				con.getForWhom().sendRawMessage("処理が実行できませんでした 時間をおいてから再実行してください");
			}
		} else {
			con.getForWhom().sendRawMessage("正しく入力されませんでした");
		}
		
		return null;
	}
	
	@Override
	public String getPromptText(ConversationContext context) {
		
		return "金額を入力してください>>>";
	}
	
	
}
