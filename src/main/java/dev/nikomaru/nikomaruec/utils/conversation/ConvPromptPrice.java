package dev.nikomaru.nikomaruec.utils.conversation;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.events.SellClickEvent;
import java.util.regex.Pattern;
import org.bukkit.conversations.Conversation;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class ConvPromptPrice extends StringPrompt {

	final String nurture_num = "^[1-9][0-9]*$";
	final Pattern p1 = Pattern.compile (nurture_num);

	@Override public Prompt acceptInput (@NotNull ConversationContext con, String price) {
		if (p1.matcher (price).matches ()) {

			long pr = Long.parseLong (price);

			Player p = (Player) con.getForWhom ();

			if (p.getUniqueId () == SellClickEvent.getData ().get (1)) {
				SellClickEvent.getData ().add (pr);
				ConversationFactory cf = new ConversationFactory (NikomaruEC.getPlugin ());
				Conversation conv2 = cf.withFirstPrompt (new ConvPromptDescription ()).withLocalEcho (true)
						.buildConversation ((p));
				conv2.begin ();

				new BukkitRunnable () {

					@Override public void run () {
						conv2.abandon ();
						if (SellClickEvent.getData ().size () <= 2) {
							p.sendMessage ("入力がないため処理を中断しました 説明をなしで出品する場合はコマンドから出品してください");
						}
					}
				}.runTaskLater (NikomaruEC.getPlugin (), 20 * 15);

				con.getForWhom ().sendRawMessage ("金額は" + price + "で処理しました");
			} else {
				con.getForWhom ().sendRawMessage ("処理が実行できませんでした 時間をおいてから再実行してください");
			}
		} else {
			con.getForWhom ().sendRawMessage ("正しく入力されませんでした");
		}

		return null;
	}

	@Override public @NotNull String getPromptText (@NotNull ConversationContext context) {

		return "金額を入力してください>>>";
	}

}
