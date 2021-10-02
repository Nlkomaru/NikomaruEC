package dev.nikomaru.nikomaruec.gui.ec;

import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;


@SuppressWarnings({"ALL", "DuplicatedCode"})
public class NowStockChestGUI {

    // 未来へのヒント indexOf()を使用すること
    final SetItemData setItemData = new SetItemData();

    public Inventory nowPlayerStock(Player p, int pages) {

        MakeGUI makegui = new MakeGUI();
        Inventory gui = Bukkit.createInventory(p, 54, makegui.getNowStockChest());
        int i = 0;
        int num = 45;
        int j = 0;
        while (i < num) {
	
	        int itemNum = (pages - 1) * 45 + i;
	
	        if (StockDataList.getStocks ().size () > itemNum) {
		        List<Object> stock = StockDataList.getStocks ().get (itemNum);
		
		
		        if (stock.get (1).equals (p.getUniqueId ())) {
			        GetItemMeta getItemMeta = new GetItemMeta ();
			
			
			        gui.setItem (i,getItemMeta.setItemMeta (stock));
			
			
			        j++;
	            }
            }
            else {
	            gui.setItem (i,setItemData.getNoDataGlassItem ());
	
            }
	        i++;
        }
	    gui.setItem (45,setItemData.getPrevItem ());
	    gui.setItem (46,setItemData.getReloadItem ());
	    gui.setItem (47,setItemData.getNextItem ());
	    gui.setItem (48,setItemData.getReturnedItem ());
	    gui.setItem (49,setItemData.getBuyItem ());
	    gui.setItem (50,setItemData.getBuyHistoryItem ());
	    gui.setItem (51,setItemData.getSellHistoryItem ());
	    gui.setItem (52,setItemData.getTerminalItem ());
	    gui.setItem (53,setItemData.getCloseItem ());
	
	
	    return gui;
	
    }

}
