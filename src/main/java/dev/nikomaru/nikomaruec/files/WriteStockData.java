package dev.nikomaru.nikomaruec.files;

import dev.nikomaru.nikomaruec.utils.StockDataList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class WriteStockData {
    //アイテムのデータを保存する処理をする予定

    private static final String COMMA = ",";

    public static void saveData() throws IOException {
        List<List<Object>> test = StockDataList.getStocks();

        // {itemStack} {player uuid} {price} {description} {time}

        String dir = "plugins\\NikomaruEC";
        String path = "plugins\\NikomaruEC\\test.csv";

        File directory = new File(dir);
        if(!directory.exists()) {
            try {
                Files.createDirectory(Path.of(dir));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        File file = new File(path);
        if (!file.exists()) {
            if (file.createNewFile()) {
                System.out.println("ファイルの作成に成功しました。");
            } else {
                System.out.println("ファイルの作成に失敗しました。");
            }
        }


        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(path));

        for (List<Object> objects : test) {

            try {
                fileWriter.write(objects.get(0).toString());
                fileWriter.write(COMMA);
                fileWriter.write(objects.get(1).toString());
                fileWriter.write(COMMA);
                fileWriter.write( objects.get(2).toString());
                fileWriter.write(COMMA);
                fileWriter.write((String)objects.get(3));
                fileWriter.write(COMMA);
                fileWriter.write( objects.get(4).toString());
                fileWriter.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fileWriter.flush();

            }

        }
    }
}