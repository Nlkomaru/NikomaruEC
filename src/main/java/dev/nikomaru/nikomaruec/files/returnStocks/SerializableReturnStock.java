package dev.nikomaru.nikomaruec.files.returnStocks;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SerializableReturnStock {
	HashMap<UUID,List<List<Object>>> returnStock;
	
	public SerializableReturnStock(HashMap<UUID,List<List<Object>>> returnStock) {
		this.returnStock = returnStock;
	}
	
	public HashMap<UUID,List<List<Object>>> getStocks() {
		return returnStock;
	}
}
