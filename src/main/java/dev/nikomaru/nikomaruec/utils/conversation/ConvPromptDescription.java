package dev.nikomaru.nikomaruec.utils.conversation;

import dev.nikomaru.nikomaruec.events.SellClickEvent;
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
    public Prompt acceptInput(ConversationContext con, String description) {

        Player p = (Player) con.getForWhom();

        SellClickEvent.getData().get(p.getUniqueId()).add(description);

        ZonedDateTime nowTime = ZonedDateTime.now();
        ZonedDateTime limitTime = nowTime.plusDays(7);
        SellClickEvent.getData().get(p.getUniqueId()).add(limitTime);

        StockDataList.getStocks().add(SellClickEvent.getData().get(p.getUniqueId()));

        System.out.println(SellClickEvent.getData().get(p.getUniqueId()));
        System.out.println(StockDataList.getStocks());
        // {itemStack} {player uuid} {price} {description} {time}
        con.getForWhom().sendRawMessage(
                ChatColor.GREEN + "説明は、「" + ChatColor.WHITE + description + ChatColor.GREEN + "」で処理しました");
        con.getForWhom().sendRawMessage(ChatColor.DARK_GREEN + "出品が完了しました");

        return null;
    }

    @Override
    public @NotNull String getPromptText(@NotNull ConversationContext context) {

        return ChatColor.AQUA + "説明を入力してください>>>";
    }
}
