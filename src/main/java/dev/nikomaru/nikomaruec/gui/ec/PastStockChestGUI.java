package dev.nikomaru.nikomaruec.gui.ec;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class PastStockChestGUI {

    public static final List<List<Object>> returns = new ArrayList<>();

    //返却された在庫
    public static @NotNull List<List<Object>> getReturns() {
        return returns;
    }

}
