/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class SetStockItemMeta {
    //アイテムのメタデータを設定するクラス
    public ItemStack setItemMeta (ArrayList<Object> stock) {

        SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

        DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm");

        String limitTime = format.format ((ZonedDateTime) stock.get (4));
        ItemStack item = ChangeItemData.decode (stock.get (0).toString ());
        OfflinePlayer Seller = Bukkit.getOfflinePlayer ((UUID) stock.get (1));
        String name = Seller.getName ();
        String price = Long.valueOf ((long) stock.get (2)).toString ();
        String description = stock.get (3).toString ();

        return setTemplateItemData.getSellerItem (item,Objects.requireNonNull (name),price,limitTime,description);
    }

    public ItemStack setReturnItemMeta (ArrayList<Object> stock) {

        SetTemplateItemData setTemplateItemData = new SetTemplateItemData ();

        DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm");

        String limitTime = format.format ((ZonedDateTime) stock.get (4));
        ItemStack item = ChangeItemData.decode (stock.get (0).toString ());
        OfflinePlayer Seller = Bukkit.getOfflinePlayer ((UUID) stock.get (1));
        String name = Seller.getName ();
        String price = Long.valueOf ((long) stock.get (2)).toString ();
        String description = stock.get (3).toString ();

        return setTemplateItemData.getReturnedItem (item,Objects.requireNonNull (name),price,limitTime,description);
    }
}
