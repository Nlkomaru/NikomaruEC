package dev.nikomaru.nikomaruec.files.stocks;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;


public class ReadStockData {
    //アイテムのデータを取得する処理をする予定
    public static void readData() {

        // {itemStack} {player uuid} {price} {description} {time}

        String dir = "plugins\\NikomaruEC";
        String path = "plugins\\NikomaruEC\\test.dat";





        try {
            ObjectInputStream objInStream
                    = new ObjectInputStream(
                    new FileInputStream(path));

            SerializableStock ss = (SerializableStock) objInStream.readObject();

            objInStream.close();

            System.out.println(ss.getStocks());

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
