package dev.nikomaru.nikomaruec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MakeList {

		public static final List<List<Object>> stocks = new ArrayList<>();

		public static List<List<Object>> getStocks() {return stocks;}


		static HashMap<UUID, Integer> nowBuyPage = new HashMap<>();

		public static HashMap<UUID, Integer> getNowBuyPage() {return nowBuyPage;}


		static HashMap<UUID, Integer> nowStockPage = new HashMap<>();

		public static HashMap<UUID, Integer> getNowStockPage() {return nowStockPage;}

}
