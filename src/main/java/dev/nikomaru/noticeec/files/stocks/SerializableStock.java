/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.stocks;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializableStock implements Serializable {
    @Serial
    private static final long serialVersionUID = -8549839403246768345L;
    ArrayList<ArrayList<Object>> stock;

    public SerializableStock (ArrayList<ArrayList<Object>> stock) {
        this.stock = stock;
    }

    public ArrayList<ArrayList<Object>> getStocks () {
        return stock;
    }
}
