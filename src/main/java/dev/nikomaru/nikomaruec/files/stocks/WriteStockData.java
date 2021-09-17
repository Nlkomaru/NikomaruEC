package dev.nikomaru.nikomaruec.files.stocks;

import dev.nikomaru.nikomaruec.NikomaruEC;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class WriteStockData {
    //アイテムのデータを保存する処理をする予定


    public static void saveData() {
        List<List<Object>> rawStocks = NikomaruEC.getStocks();

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


        List<List<Object>> stoneStock = new ArrayList<>();

        for (List<Object> objects : rawStocks) {


            List<Object> serializationData = new ArrayList<>();
            ItemStack item = (ItemStack) objects.get(0);        // {itemStack}
            UUID uuid = (UUID) objects.get(1);        //{player uuid}
            long price = (long) objects.get(2);        //{price}
            String description = (String) objects.get(3);        //{description}
            ZonedDateTime time = (ZonedDateTime) objects.get(4);        //{time}


            String encodeObject = null;
            try {

                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                BukkitObjectOutputStream boos = new BukkitObjectOutputStream(baos);
                boos.writeObject(item);
                boos.flush();

                byte[] serializedObject = baos.toByteArray();
                encodeObject = Base64.getEncoder().encodeToString(serializedObject);

            } catch (IOException e) {
                e.printStackTrace();
            }

            serializationData.add(encodeObject);
            serializationData.add(uuid);
            serializationData.add(price);
            serializationData.add(description);
            serializationData.add(time);

            stoneStock.add(serializationData);
        }


        try {
            ObjectOutputStream objOutStream = new ObjectOutputStream(new FileOutputStream(path));
            SerializableStock ss = new SerializableStock(stoneStock);

            objOutStream.writeObject(ss);
            objOutStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}