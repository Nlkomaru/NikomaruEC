package dev.nikomaru.nikomaruec.utils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.ItemStack;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

public class GetItemMeta {
    public ItemStack setItemMeta(List<Object> stock) {
	
	    SetItemData setItemData = new SetItemData ();
	
	    DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm");
	    String limitTime = format.format ((ZonedDateTime) stock.get (4));
	
	    ItemStack item = (ItemStack) stock.get (0);
	    OfflinePlayer Seller = Bukkit.getOfflinePlayer ((UUID) stock.get (1));
	    String name = Seller.getName ();
	    String price = Long.valueOf ((long) stock.get (2)).toString ();
	    String description = (String) stock.get (3);
	
	    return setItemData.getSellerItem (item,name,price,limitTime,description);
    }
	
	public ItemStack setReturnedItemMeta (List<Object> stock) {
		
		SetItemData setItemData = new SetItemData ();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern ("yyyy/MM/dd HH:mm");
		String limitTime = format.format ((ZonedDateTime) stock.get (4));
		
		ItemStack item = (ItemStack) stock.get (0);
		OfflinePlayer Seller = Bukkit.getOfflinePlayer ((UUID) stock.get (1));
		String name = Seller.getName ();
		String price = Long.valueOf ((long) stock.get (2)).toString ();
		String description = (String) stock.get (3);
		
		return setItemData.getReturnedItem (item,name,price,limitTime,description);
	}
}
