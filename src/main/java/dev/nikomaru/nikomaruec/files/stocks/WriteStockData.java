package dev.nikomaru.nikomaruec.files.stocks;

import dev.nikomaru.nikomaruec.utils.StockDataList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteStockData {
    //アイテムのデータを保存する処理をする予定

    private static final String COMMA = ",";

    public static void saveData(){
        List<List<Object>> test = StockDataList.getStocks();

        // {itemStack} {player uuid} {price} {description} {time}

        String dir = "plugins\\NikomaruEC";
        String path = "plugins\\NikomaruEC\\test.dat";

        File directory = new File(dir);
        if (!directory.exists()) {
            try {
                Files.createDirectory(Path.of(dir));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(path);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("ファイルの作成に成功しました。");
                } else {
                    System.out.println("ファイルの作成に失敗しました。");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

//https://www.youtube.com/watch?v=xIl5v9Iyb08
        for (List<Object> objects : test) {

            objects.get(0);        // {itemStack}
            objects.get(1);        //{player uuid}
            objects.get(2);        //{price}
            objects.get(3);        //{description}
            objects.get(4);        //{time}

            }


        }
}