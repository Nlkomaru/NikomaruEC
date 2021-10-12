/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.history;

import org.bukkit.inventory.ItemStack;

import java.util.UUID;

/*
 * アイテム名
 * 販売者または購入者
 * 金額
 * 購入日時
 */

public class CSVToHistory {
    public void writePurchaseHistory (UUID buyerUUID,ItemStack item,UUID sellerUUID,long price) {
        String itemName = item.getItemMeta ().getLocalizedName ();
    }

    public void writeSalesHistory (UUID sellerUUID,ItemStack item,UUID buyerUUID,long price) {
        String itemName = item.getItemMeta ().getLocalizedName ();
    }
}
