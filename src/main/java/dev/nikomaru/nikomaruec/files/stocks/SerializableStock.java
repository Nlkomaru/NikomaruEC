package dev.nikomaru.nikomaruec.files.stocks;

import java.io.Serializable;
import java.util.List;

public class SerializableStock implements Serializable {
    List<List<Object>> stock;

    public SerializableStock(List<List<Object>> stock) {
        this.stock = stock;
    }

    public List<List<Object>> getStocks() {
        return stock;
    }

}
