package dev.nikomaru.nikomaruec.utils;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.UUID;

public class StockDataList {


    static @NotNull HashMap<UUID, Integer> nowBuyPage = new HashMap<>();
    static @NotNull HashMap<UUID, Integer> nowStockPage = new HashMap<>();


    public static @NotNull HashMap<UUID, Integer> getNowBuyPage() {
        return nowBuyPage;
    }

    public static @NotNull HashMap<UUID, Integer> getNowStockPage() {
        return nowStockPage;
    }

}
