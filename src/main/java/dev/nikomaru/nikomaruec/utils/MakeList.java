package dev.nikomaru.nikomaruec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class MakeList {

		public static final List<List<Object>> stocks = new ArrayList<>();
		static HashMap<UUID, Integer> nowPage = new HashMap<>();

		public static List<List<Object>> getStocks() {
				return stocks;
		}

		public static HashMap<UUID, Integer> getNowPage() {
				return nowPage;
		}
}
