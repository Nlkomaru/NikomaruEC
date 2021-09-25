package dev.nikomaru.nikomaruec.files.returnStocks;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SerializableReturnStock implements Serializable {
	private static final long serialVersionUID = - 3288388669213934496L;
	HashMap<UUID,List<List<Object>>> returnStock;
	
	public SerializableReturnStock (HashMap<UUID,List<List<Object>>> returnStock) {
		this.returnStock = returnStock;
	}
	
	public HashMap<UUID,List<List<Object>>> getStocks () {
		return returnStock;
	}
}
