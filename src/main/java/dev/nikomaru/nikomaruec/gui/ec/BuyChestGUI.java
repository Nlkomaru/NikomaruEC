package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("DuplicatedCode")
public class BuyChestGUI {

    //購入用のGUIを作成する処理をする予定

    final SetItemData setItemData = new SetItemData();

    public Inventory Buy(Player p, int pages) {

        MakeGUI makegui = new MakeGUI();
        Inventory gui = Bukkit.createInventory(p,54, makegui.getBuyChest());
        int i = 0;
        int num = 45;
        int stockSize = NikomaruEC.getStocks().size();

        while (i < num) {

            if ((pages - 1) * 45 + i < stockSize) {
                List<Object> stock = NikomaruEC.getStocks().get((pages - 1) * 45 + i);

                ZonedDateTime nowTime = ZonedDateTime.now();

                if (nowTime.isBefore((ZonedDateTime) stock.get(4))) {

                    Player Seller = (Player) Bukkit.getOfflinePlayer((UUID) stock.get(1));

                    DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
                    String limitTime = format.format((ZonedDateTime) stock.get(4));

                    // {itemStack} {player uuid} {price} {description} {time}

                    ItemStack item = (ItemStack) stock.get(0);
                    String name = Seller.getName();
                    String price = Long.valueOf((long) stock.get(2)).toString();

                    String description = (String) stock.get(3);

                    gui.setItem(i, setItemData.getSellerItem(item, name, price, limitTime, description));

                    i++;
                } else {
                    NikomaruEC.getStocks().get((pages - 1) * 45 + i);
                }
            } else {
                gui.setItem(i, setItemData.getNoDataGlassItem());
                i++;
            }
        }

        gui.setItem(45, setItemData.getPrevItem());
        gui.setItem(46, setItemData.getReloadItem());
        gui.setItem(47, setItemData.getNextItem());
        gui.setItem(48, setItemData.getLeftoversItem());
        gui.setItem(49, setItemData.getStoreItem());
        gui.setItem(50, setItemData.getBuyHistoryItem());
        gui.setItem(51, setItemData.getSellHistoryItem());
        gui.setItem(52, setItemData.getTerminalItem());
        gui.setItem(53, setItemData.getCloseItem());

        return gui;
    }
}
