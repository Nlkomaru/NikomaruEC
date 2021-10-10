/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StockDataList {

    private static HashMap<UUID,Integer> nowBuyPage = new HashMap<> ();
    private static HashMap<UUID,Integer> nowStockPage = new HashMap<> ();
    private static HashMap<UUID,Integer> selectNum = new HashMap<> ();
    private static HashMap<UUID,Integer> returnPage = new HashMap<> ();
    private static HashMap<UUID,List<Object>> sellData = new HashMap<> ();
    private static List<List<Object>> stocks = new ArrayList<> ();
    private static HashMap<UUID,List<List<Object>>> returnStocks = new HashMap<> ();

    public static HashMap<UUID,Integer> getNowBuyPage () {
        return nowBuyPage;
    }

    public static HashMap<UUID,Integer> getNowStockPage () {
        return nowStockPage;
    }

    public static HashMap<UUID,Integer> getSelectNum () {
        return selectNum;
    }

    public static HashMap<UUID,Integer> getReturnPage () {
        return returnPage;
    }

    public static void putNowBuyPage (UUID uuid,int page) {
        nowBuyPage.put (uuid,page);
    }

    public static void putNowStockPage (UUID uuid,int page) {
        nowStockPage.put (uuid,page);
    }

    public static void putSelectNum (UUID uuid,int page) {
        selectNum.put (uuid,page);
    }

    public static void putReturnPage (UUID uuid,int page) {
        returnPage.put (uuid,page);
    }

    public static HashMap<UUID,List<Object>> getData () {
        return sellData;
    }

    public static void addData (UUID uuid,Object data) {
        sellData.get (uuid).add (data);
    }

    public static void putNewData (UUID uuid) {
        sellData.put (uuid,new ArrayList<> ());
    }

    public static List<List<Object>> getStocks () {
        return stocks;
    }

    public static void setStocks (List<List<Object>> data) {
        stocks = data;
    }

    public static void addStocks (List<Object> list) {
        stocks.add (list);
    }

    public static void removeStocks (int i) {
        stocks.remove (i);
    }

    public static HashMap<UUID,List<List<Object>>> getReturnStocks () {
        return returnStocks;
    }

    public static void setReturnStocks (HashMap<UUID,List<List<Object>>> data) {
        returnStocks = data;
    }

    public static void addReturnStocks (UUID uuid,List<Object> list) {
        returnStocks.get (uuid).add (list);
    }

    public static void removeReturnStocks (UUID uuid,int i) {
        returnStocks.get (uuid).remove (i);
    }

    public static void setNewReturnStocks (UUID uuid) {
        returnStocks.computeIfAbsent (uuid,k -> new ArrayList<> ());
    }

}
