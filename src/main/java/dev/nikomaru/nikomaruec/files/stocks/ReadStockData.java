package dev.nikomaru.nikomaruec.files.stocks;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;


public class ReadStockData {
    //アイテムのデータを取得する処理をする予定
    public static List<List<Object>> readData() {

        // {itemStack} {player uuid} {price} {description} {time}

        String dir = "plugins\\NikomaruEC";
        String path = "plugins\\NikomaruEC\\test.dat";

        List<List<Object>> storeStocks = new ArrayList<>();

        List<List<Object>> restoreStocks = new ArrayList<>();

        try {
            ObjectInputStream objInStream = new ObjectInputStream(new FileInputStream(path));

            SerializableStock ss = (SerializableStock) objInStream.readObject();

            objInStream.close();

            storeStocks = ss.getStocks();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        // {itemStack} {player uuid} {price} {description} {time}

        for(List<Object> objects : storeStocks){
            List<Object> stock = new ArrayList<>();

            String stoneItem = (String) objects.get(0);
            UUID uuid = (UUID) objects.get(1);
            Long price = (Long) objects.get(2);
            String description = (String) objects.get(3);
            ZonedDateTime time = (ZonedDateTime) objects.get(4);

            ItemStack item = null;
            try {
                byte[] serializedObject = Base64.getDecoder().decode(stoneItem);
                ByteArrayInputStream bais = new ByteArrayInputStream(serializedObject);
                BukkitObjectInputStream bois = new BukkitObjectInputStream(bais);

                item = (ItemStack) bois.readObject();

            }catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            stock.add(item);
            stock.add(uuid);
            stock.add(price);
            stock.add(description);
            stock.add(time);

            restoreStocks.add(stock);
        }

        return restoreStocks;
    }
}
