package dev.nikomaru.nikomaruec.utils;

import java.util.HashMap;
import java.util.UUID;

public class StockDataList {


    static HashMap<UUID, Integer> nowBuyPage = new HashMap<>();
    static HashMap<UUID, Integer> nowStockPage = new HashMap<>();



    public static HashMap<UUID, Integer> getNowBuyPage() {
        return nowBuyPage;
    }

    public static HashMap<UUID, Integer> getNowStockPage() {
        return nowStockPage;
    }

}
