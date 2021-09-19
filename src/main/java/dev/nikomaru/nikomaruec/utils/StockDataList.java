package dev.nikomaru.nikomaruec.utils;

import java.util.HashMap;
import java.util.UUID;

public class StockDataList {
    
    
    static HashMap<UUID,Integer> nowBuyPage = new HashMap<> ();
    static HashMap<UUID,Integer> nowStockPage = new HashMap<> ();
    static HashMap<UUID,Integer> selectNum = new HashMap<> ();
    static HashMap<UUID,HashMap<Integer,Integer>> playerNowStockNum = new HashMap<> ();
    
    
    public static HashMap<UUID,Integer> getNowBuyPage () {
        return nowBuyPage;
    }
    
    public static HashMap<UUID,Integer> getNowStockPage () {
        return nowStockPage;
    }
    
    public static HashMap<UUID,Integer> getSelectNum () {
        return selectNum;
    }
    
    public static HashMap<UUID,HashMap<Integer,Integer>> getPlayerNowStockNum () {
        return playerNowStockNum;
    }
    
}
