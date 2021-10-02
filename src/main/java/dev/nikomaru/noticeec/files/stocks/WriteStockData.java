package dev.nikomaru.noticeec.files.stocks;

import dev.nikomaru.noticeec.files.MakeFile;
import dev.nikomaru.noticeec.utils.StockDataList;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WriteStockData {
	//アイテムのデータを保存する処理をする予定
	
	
	public static void saveData () {
		List<List<Object>> rawStocks = StockDataList.getStocks ();
		
		// {itemStack} {player uuid} {price} {description} {time}
		
		String dir = "plugins\\NoticeEC";
		String path = "plugins\\NoticeEC\\stock.dat";
		
		
		List<List<Object>> stoneStock = new ArrayList<> ();
		if (! (rawStocks.isEmpty ())) {
			MakeFile.makeFile (dir,path);
			for (List<Object> objects : rawStocks) {
				
				
				List<Object> serializationData = new ArrayList<> ();
				String item = (String) objects.get (0);        // {itemStack}
				UUID uuid = (UUID) objects.get (1);        //{player uuid}
				long price = (long) objects.get (2);        //{price}
				String description = (String) objects.get (3);        //{description}
				ZonedDateTime time = (ZonedDateTime) objects.get (4);        //{time}
				
				
				serializationData.add (item);
				serializationData.add (uuid);
				serializationData.add (price);
				serializationData.add (description);
				serializationData.add (time);
				
				stoneStock.add (serializationData);
			}
			
			
			try {
				ObjectOutputStream objOutStream = new ObjectOutputStream (new FileOutputStream (path));
				SerializableStock ss = new SerializableStock (stoneStock);
				objOutStream.writeObject (ss);
				objOutStream.flush ();
				objOutStream.reset ();
				objOutStream.close ();
				
				
			} catch (IOException e) {
				e.printStackTrace ();
			}
			
			
		}
		
	}
}