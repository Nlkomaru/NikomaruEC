/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;

public class MakeGUI {
    //gui作成用のクラス
    public Component getBuyChest () {
        return getComponent ("物品購入所",100,255,130);
    }

    private Component getComponent (String name,int r,int g,int b) {
        return Component.text (name,TextColor.color (r,g,b));
    }

    public Component getSellChest () {
        return getComponent ("物品販売所",251,107,255);
    }

    public Component getNowStockChest () {
        return getComponent ("出品中の在庫",255,0,255);
    }

    public Component getTerminalChest () {
        return getComponent ("メニュー",100,149,237);
    }

    public Component getBuyAcceptChest () {
        return getComponent ("購入決定画面",50,205,50);
    }

    public Component getReturnedChest () {
        return getComponent ("返却された在庫",139,0,139);
    }
}
