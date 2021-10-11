/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.commands;

import dev.nikomaru.noticeec.NoticeEC;
import dev.nikomaru.noticeec.files.Config;
import dev.nikomaru.noticeec.utils.ChangeItemData;
import dev.nikomaru.noticeec.utils.StockDataList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class EasySell implements CommandExecutor {
    //コマンドから簡単に出品できる処理をするコマンド予定
    //フォーマット /nes [金額] [説明]
    static ArrayList<Object> easySellData;
    final String nurture_num = "^[0-9]{1,18}$";
    final Pattern p1 = Pattern.compile (nurture_num);

    @Override
    public boolean onCommand (@NotNull CommandSender sender,@NotNull Command command,@NotNull String label,String[] args) {

        if (!(sender instanceof Player)) {
            return false;
        }
        Player p = (Player) sender;
        easySellData = new ArrayList<> ();

        if (p.getInventory ().getItemInMainHand ().getType () == Material.AIR) {
            p.sendMessage (ChatColor.YELLOW + "アイテムをメインハンドに持ってください");
            return false;
        }

        if (!(args.length == 1 || args.length == 2)) {
            p.sendMessage (ChatColor.YELLOW + "コマンドの長さが不適切です");
            return false;
        }

        if (!p1.matcher (args[0]).matches ()) {
            p.sendMessage (ChatColor.YELLOW + "金額は自然数を入力してください");
            return false;
        }

        // {itemStack} {player uuid} {price} {description} {time}
        long price = Long.parseLong (args[0]);
        Config config = new Config (NoticeEC.getPlugin ());

        if (config.getMinPrice () > price || price > config.getMaxPrice ()) {
            p.sendMessage (ChatColor.YELLOW + "指定された金額はサーバーにより指定された金額より大きすぎます");
            return false;
        }

        easySellData.add (ChangeItemData.encode (p.getInventory ().getItemInMainHand ()));
        easySellData.add (p.getUniqueId ());
        easySellData.add (price);
        easySellData.add (args.length == 1 ? "説明はありません" : args[1]);

        ZonedDateTime nowTime = ZonedDateTime.now ();
        //        ZonedDateTime limitTime = nowTime.plusDays (config.getAddDays ()).plusHours (config.getAddHours ());
        ZonedDateTime limitTime = nowTime.plusMinutes (1);
        easySellData.add (limitTime);
        p.sendMessage (ChatColor.GREEN + String.format ("%s円で、説明は「%s」で処理しました",easySellData.get (2).toString (),
                easySellData.get (3).toString ()));
        p.getInventory ().setItemInMainHand (new ItemStack (Material.AIR));

        StockDataList.addStocks (easySellData);
        return true;
    }
}