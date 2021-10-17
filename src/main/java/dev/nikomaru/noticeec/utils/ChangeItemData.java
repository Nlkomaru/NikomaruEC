/*
 * NoticeEC
 *
 * Written in 2021 by nikomaru
 *
 * To the extent possible under law, the author(s) have dedicated all copyright and related and neighboring rights to this software to the public domain worldwide. This software is distributed without any warranty.
 *
 * You should have received a copy of the CC0 Public Domain Dedication along with this software. If not, see <http://creativecommons.org/publicdomain/zero/1.0/>.
 */

package dev.nikomaru.noticeec.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ChangeItemData {
    //アイテムをエンコード、デコードするクラス
    public static String encode (ItemStack item) {
        //エンコード
        String encodeObject = null;
        try {

            ByteArrayOutputStream baos = new ByteArrayOutputStream ();
            BukkitObjectOutputStream boos = new BukkitObjectOutputStream (baos);
            boos.writeObject (item);
            boos.flush ();

            byte[] serializedObject = baos.toByteArray ();
            encodeObject = Base64.getEncoder ().encodeToString (serializedObject);
        } catch (IOException e) {
            e.printStackTrace ();
        }

        return encodeObject;
    }

    public static ItemStack decode (String encodeItem) {
        //デコード
        ItemStack item = null;
        try {
            byte[] serializedObject = Base64.getDecoder ().decode (encodeItem);
            ByteArrayInputStream bais = new ByteArrayInputStream (serializedObject);
            BukkitObjectInputStream bois = new BukkitObjectInputStream (bais);

            item = (ItemStack) bois.readObject ();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace ();
        }
        return item;
    }
}

