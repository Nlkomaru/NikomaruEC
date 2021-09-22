package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.utils.ChangeItemData;
import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@SuppressWarnings("DuplicatedCode")
public class BuyChestGUI {

    //購入用のGUIを作成する処理をする予定

    final SetItemData setItemData = new SetItemData();

    public Inventory Buy(Player p, int pages) {

        MakeGUI makegui = new MakeGUI();
        Inventory gui = Bukkit.createInventory(p, 54, makegui.getBuyChest());
        int i = 0;
        int num = 45;
        int stockSize = NikomaruEC.getStocks().size();
        List<Object> stock;
        while (i < num) {

            if ((pages - 1) * 45 + i < stockSize) {
                stock = NikomaruEC.getStocks().get((pages - 1) * 45 + i);


                ZonedDateTime nowTime = ZonedDateTime.now();

                if (nowTime.isBefore((ZonedDateTime) stock.get(4))) {
                    GetItemMeta getItemMeta = new GetItemMeta();


                    gui.setItem(i, getItemMeta.setItemMeta(stock));
                    i++;
                } else {
                    // {itemStack} {player uuid} {price} {description} {time}
                    List<Object> returnStock = new ArrayList<> ();
                    String encodeReturnStock = ChangeItemData.encode ((ItemStack) NikomaruEC.getStocks ().get ((pages - 1) * 45 + i).get (0));
                    UUID uuid = (UUID) NikomaruEC.getStocks ().get ((pages - 1) * 45 + i).get (1);
                    Long price = (Long) NikomaruEC.getStocks ().get ((pages - 1) * 45 + i).get (2);
                    ZonedDateTime time = ZonedDateTime.now ();
                    
                    returnStock.add (encodeReturnStock);
                    returnStock.add (uuid);
                    returnStock.add (price);
                    returnStock.add (price);
                    returnStock.add (time);
                    NikomaruEC.getReturnStocks ().get (uuid).add (returnStock);
                    
                    NikomaruEC.getStocks ().remove ((pages - 1) * 45 + i);
                    
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
