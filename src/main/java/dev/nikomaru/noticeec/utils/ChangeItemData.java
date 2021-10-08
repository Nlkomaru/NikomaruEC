/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ChangeItemData {
	public static String encode (ItemStack item) {
		
		String encodeObject = null;
		try {
			
			ByteArrayOutputStream baos = new ByteArrayOutputStream ();
			BukkitObjectOutputStream boos = new BukkitObjectOutputStream (baos);
			boos.writeObject (item);
			boos.flush ();
			
			byte[] serializedObject = baos.toByteArray ();
			encodeObject = Base64.getEncoder ().encodeToString (serializedObject);
			
		} catch (IOException e) {
			e.printStackTrace ();
		}
		
		return encodeObject;
	}
	
	public static ItemStack decode (String encodeItem) {
		ItemStack item = null;
		try {
			byte[] serializedObject = Base64.getDecoder ().decode (encodeItem);
			ByteArrayInputStream bais = new ByteArrayInputStream (serializedObject);
			BukkitObjectInputStream bois = new BukkitObjectInputStream (bais);
			
			item = (ItemStack) bois.readObject ();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace ();
		}
		return item;
	}
}

