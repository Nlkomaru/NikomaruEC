/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.stocks;

import java.io.Serializable;
import java.util.List;

public class SerializableStock implements Serializable {
    private static final long serialVersionUID = -8549839403246768345L;

    List<List<Object>> stock;

    public SerializableStock (List<List<Object>> stock) {
        this.stock = stock;
    }

    public List<List<Object>> getStocks () {
        return stock;
    }

}
