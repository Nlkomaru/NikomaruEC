package dev.nikomaru.nikomaruec.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.jetbrains.annotations.NotNull;

public class MakeGUI {
    private @NotNull Component getComponent(String name, int r, int g, int b) {
        return Component.text(name, TextColor.color(r, g, b));
    }

    public Component getBuyChest(){
        return getComponent("物品購入所", 100, 255, 130);
    }
    public Component getSellChest(){
        return getComponent("物品販売所", 251, 107, 255);
    }
    public Component getNowStockChest(){
        return getComponent("出品中の在庫", 255, 0, 255);
    }
    public Component getTerminalChest(){
        return getComponent("メニュー", 100, 149, 237);
    }
}
