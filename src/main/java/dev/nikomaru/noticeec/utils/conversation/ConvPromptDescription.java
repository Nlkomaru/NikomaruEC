/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.utils.conversation;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.files.Config;
import dev.nikomaru.noticeec.files.stocks.WriteStockData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;

public class ConvPromptDescription extends StringPrompt {
    //説明を取得するクラス
    @Override
    public Prompt acceptInput (ConversationContext con,String description) {

        Player player = (Player) con.getForWhom ();
        description = description.replace (",",".");
        StockDataList.addData (player.getUniqueId (),description);

        ZonedDateTime nowTime = ZonedDateTime.now ();
        Config config = new Config (NoticeEC.getPlugin ());
        ZonedDateTime limitTime = nowTime.plusDays (config.getAddDays ()).plusHours (config.getAddHours ());
        StockDataList.addData (player.getUniqueId (),limitTime);

        StockDataList.getStocks ().add (StockDataList.getData ().get (player.getUniqueId ()));

        // {itemStack} {player uuid} {price} {description} {time}
        con.getForWhom ().sendRawMessage (
                ChatColor.GREEN + "説明は、「" + ChatColor.WHITE + description + ChatColor.GREEN + "」で処理しました");
        con.getForWhom ().sendRawMessage (ChatColor.DARK_GREEN + "出品が完了しました");

        WriteStockData writeStockData = new WriteStockData ();
        writeStockData.saveData ();
        return null;
    }

    @Override
    public @NotNull String getPromptText (@NotNull ConversationContext context) {

        return ChatColor.AQUA + "説明を入力してください>>>";
    }
}
