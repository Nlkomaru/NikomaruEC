/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.returnStocks;

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
