package dev.nikomaru.nikomaruec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StockDataList {

	public static final List<List<Object>> stocks = new ArrayList<> ();
	static final HashMap<UUID, Integer> nowBuyPage = new HashMap<> ();
	static final HashMap<UUID, Integer> nowStockPage = new HashMap<> ();

	public static List<List<Object>> getStocks () {
		return stocks;
	}

	public static HashMap<UUID, Integer> getNowBuyPage () {
		return nowBuyPage;
	}

	public static HashMap<UUID, Integer> getNowStockPage () {
		return nowStockPage;
	}

}
