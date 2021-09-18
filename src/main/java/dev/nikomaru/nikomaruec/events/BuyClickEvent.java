package dev.nikomaru.nikomaruec.events;

import dev.nikomaru.nikomaruec.NikomaruEC;
import dev.nikomaru.nikomaruec.gui.ec.BuyAcceptChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.BuyChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.NowStockChestGUI;
import dev.nikomaru.nikomaruec.gui.ec.TerminalChestGUI;
import dev.nikomaru.nikomaruec.utils.GetItemMeta;
import dev.nikomaru.nikomaruec.utils.MakeGUI;
import dev.nikomaru.nikomaruec.utils.SetItemData;
import dev.nikomaru.nikomaruec.utils.StockDataList;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

import static dev.nikomaru.nikomaruec.utils.StockDataList.getNowStockPage;

@SuppressWarnings("DuplicatedCode")
public class BuyClickEvent implements Listener {

    //購入用のアイテムがクリックされたら購入用GUIに飛ぶ処理をする予定
    @EventHandler
    public void clickEvent(@NotNull InventoryClickEvent e) {

        Player p = (Player) e.getWhoClicked();
        MakeGUI makegui = new MakeGUI();
        if (e.getView().title().equals(makegui.getBuyChest())) {
            if (e.getClickedInventory() != null) {
                InventoryType inv = e.getClickedInventory().getType();
                if (inv == InventoryType.CHEST) {
                    UUID playerUUID = p.getUniqueId();
                    int pages = StockDataList.getNowBuyPage().get(playerUUID);
                    int i = e.getSlot();
                    int num = i + (pages - 1) * 45;
                    int maxPage = NikomaruEC.getStocks().size() / 45;

                    //1.戻る 2.ページ数表示(更新)  3.進む  4.売れなかった  5.販売中の在庫  6.購入履歴  7.販売履歴  8.ターミナルに戻る  9.閉じる
                    if ((0 <= i && 44 >= i) && num < NikomaruEC.getStocks().size()) {

                        if (NikomaruEC.getStocks().get(num).get(1).equals(playerUUID)) {
                            SetItemData setItemData = new SetItemData();
                            e.getClickedInventory().setItem(i, setItemData.getNoticeItem());

                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    GetItemMeta getItemMeta = new GetItemMeta();
                                    e.getClickedInventory().setItem(i, getItemMeta.setItemMeta(NikomaruEC.getStocks().get(num)));
                                }
                            }.runTaskLater(NikomaruEC.getPlugin(), 20 * 2);

                        } else {
                            BuyAcceptChestGUI buyAcceptChestGUI = new BuyAcceptChestGUI();
                            p.openInventory(buyAcceptChestGUI.BuyAccept(p, num));
                        }
                    } else if (i >= 45 && i <= 47) {

                        int change = 0;

                        if (pages > 1 && i == 45) {
                            change = -1;

                        } else if (pages <= 1 && i == 47 && pages < maxPage) {
                            change = 1;

                        }

                        StockDataList.getNowBuyPage().put(playerUUID, pages + change);
                        BuyChestGUI buy = new BuyChestGUI();
                        p.openInventory(buy.Buy(p, pages + change));
                        e.setCancelled(true);

                    } else if (i == 48) {

                        //売れなかった在庫

                    } else if (i == 49) {
                        //自分の販売中の在庫

                        NowStockChestGUI nowStock = new NowStockChestGUI();
                        p.openInventory(nowStock.nowPlayerStock(p, 1));
                        getNowStockPage().put(p.getUniqueId(), 1);
                        e.setCancelled(true);

                    } else if (i == 50) {

                        //購入履歴

                    } else if (i == 51) {

                        //販売履歴

                    } else if (i == 52) {

                        //ターミナルを開く

                        TerminalChestGUI terminal = new TerminalChestGUI();
                        p.openInventory(terminal.Terminal(p));

                    } else if (i == 53) {

                        //閉じる
                        p.closeInventory();

                    }

                    e.setCancelled(true);
                }
            }
        }
    }

}