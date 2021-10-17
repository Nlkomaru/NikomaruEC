/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
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
