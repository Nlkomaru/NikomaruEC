/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.utils.conversation;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.files.Config;
import dev.nikomaru.noticeec.utils.ChangeItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.conversations.*;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class ConvPromptPrice extends StringPrompt {
    //金額を取得するクラス

    @Override
    public Prompt acceptInput (@NotNull ConversationContext con,String inputPrice) {
        final String nurture_num = "^[0-9]{1,18}$";
        final Pattern p1 = Pattern.compile (nurture_num);
        if (!p1.matcher (inputPrice).matches ()) {
            con.getForWhom ().sendRawMessage (ChatColor.YELLOW + "正しく入力されませんでした");
            return null;
        }

        Config config = new Config (NoticeEC.getPlugin ());
        long price = Long.parseLong (inputPrice);

        if (!(config.getMinPrice () <= price && price <= config.getMaxPrice ())) {
            con.getForWhom ().sendRawMessage (
                    ChatColor.YELLOW + "正しく入力されませんでした" + "\n" + String.format ("金額の範囲は%dから%dまでに制限されています",
                            config.getMinPrice (),config.getMaxPrice ()));
            return null;
        }

        Player player = (Player) con.getForWhom ();
        StockDataList.addData (player.getUniqueId (),price);
        ConversationFactory cf = new ConversationFactory (NoticeEC.getPlugin ());
        Conversation conv2 = cf.withFirstPrompt (new ConvPromptDescription ()).withLocalEcho (true)
                .buildConversation ((player));
        conv2.begin ();

        new BukkitRunnable () {
            @Override
            public void run () {
                conv2.abandon ();
                if (StockDataList.getData ().get (player.getUniqueId ()).size () <= 3) {
                    player.sendMessage (ChatColor.YELLOW + "入力がないため処理を中断しました 説明をなしで出品する場合はコマンドから出品してください");
                    player.getWorld ().dropItem (player.getLocation (),ChangeItemData.decode (
                            StockDataList.getData ().get (player.getUniqueId ()).get (0).toString ()));
                }
            }
        }.runTaskLater (NoticeEC.getPlugin (),20 * 12);

        con.getForWhom ().sendRawMessage (ChatColor.GREEN + "金額は" + price + "で処理しました");

        return null;
    }

    @Override
    public @NotNull String getPromptText (@NotNull ConversationContext context) {

        return ChatColor.AQUA + "金額を入力してください>>>";
    }
}
