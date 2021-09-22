package dev.nikomaru.nikomaruec.files.returnStocks;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ReadReturnStockData {
	public static @NotNull HashMap<UUID,List<List<Object>>> readData () {
		String path = "plugins\\NikomaruEC\\returnStock.dat";
		
		HashMap<UUID,List<List<Object>>> restoreStocks = new HashMap<> ();
		File file = new File (path);
		
		if (file.exists ()) {
			try {
				
				ObjectInputStream objInStream = new ObjectInputStream (new FileInputStream (path));
				SerializableReturnStock srs = (SerializableReturnStock) objInStream.readObject ();
				
				objInStream.close ();
				restoreStocks = srs.getStocks ();
				
			} catch (@NotNull IOException | ClassNotFoundException e) {
				e.printStackTrace ();
			}
			
		}
		
		return restoreStocks;
	}
	
}