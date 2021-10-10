/*
 * To the extent possible under law, Nikomaru has waived all copyright and related or neighboring rights to NoticeEC. This work is published from: 日本.
 */

package dev.nikomaru.noticeec.files.returnStocks;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;

public class SerializableReturnStock implements Serializable {
    @Serial
    private static final long serialVersionUID = -783284843585153721L;
    ArrayList<ArrayList<Object>> returnStock;

    public SerializableReturnStock (ArrayList<ArrayList<Object>> returnStock) {
        this.returnStock = returnStock;
    }

    public ArrayList<ArrayList<Object>> getStocks () {
        return returnStock;
    }
}
