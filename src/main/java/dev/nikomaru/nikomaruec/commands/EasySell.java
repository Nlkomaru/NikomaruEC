package dev.nikomaru.nikomaruec.commands;

import dev.nikomaru.nikomaruec.utils.StockDataList;
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
import java.util.List;
import java.util.regex.Pattern;

public class EasySell implements CommandExecutor {
    //コマンドから簡単に出品できる処理をするコマンド予定
    //フォーマット /nes [金額] [説明]

    static List<Object> easySellData;
    final String nurture_num = "^[1-9][0-9]*$";
    Pattern p1 = Pattern.compile(nurture_num);

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label,
                             String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            easySellData = new ArrayList<>();
            if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
                if (args.length == 1 || args.length == 2) {
                    if (p1.matcher(args[0]).matches()) {
                        // {itemStack} {player uuid} {price} {description} {time}
                        long price = Long.parseLong(args[0]);
                        easySellData.add(p.getInventory().getItemInMainHand());
                        easySellData.add(p.getUniqueId());
                        easySellData.add(price);

                        if (args.length == 1) {
                            easySellData.add("説明はありません");

                        } else {
                            easySellData.add(args[1]);
                        }

                        ZonedDateTime nowTime = ZonedDateTime.now();
                        ZonedDateTime limitTime = nowTime.plusDays(7);
                        easySellData.add(limitTime);
                        p.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                        StockDataList.getStocks().add(easySellData);
                    }
                }
            } else {
                p.sendMessage(ChatColor.YELLOW + "アイテムをメインハンドに持ってください");
            }

        }

        return true;

    }
}
