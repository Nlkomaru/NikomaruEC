package dev.nikomaru.nikomaruec.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SetItemData {

    public @NotNull ItemStack getPrevItem() {
        //前のページに戻るためのガラス
        ItemStack prev = new ItemStack(Material.RED_STAINED_GLASS_PANE);
        ItemMeta prev_meta = prev.getItemMeta();
        prev_meta.displayName(Component.text("前のページへ戻る", TextColor.color(232, 57, 41)));
        prev.setItemMeta(prev_meta);
        return prev;
    }

    public @NotNull ItemStack getReloadItem() {
        //ページを更新するためのガラス
        ItemStack reload = new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
        ItemMeta reload_meta = reload.getItemMeta();
        reload_meta.displayName(Component.text("ページを更新する", TextColor.color(0, 255, 0)));
        reload.setItemMeta(reload_meta);
        return reload;
    }
	
	public @NotNull ItemStack getNextItem () {
		//次のページに進むためのガラス
		ItemStack next = new ItemStack (Material.BLUE_STAINED_GLASS_PANE);
		ItemMeta next_meta = next.getItemMeta ();
		next_meta.displayName (Component.text ("次のページへ進む",TextColor.color (0,191,255)));
		next.setItemMeta (next_meta);
		return next;
	}
	
	public @NotNull ItemStack getReturnedItem () {
		//売れなかった在庫を見るためのチェスト
		ItemStack leftovers = new ItemStack (Material.CHEST);
		ItemMeta leftovers_meta = leftovers.getItemMeta ();
		leftovers_meta.displayName (Component.text ("返却された在庫",TextColor.color (246,31,255)));
		leftovers.setItemMeta (leftovers_meta);
		return leftovers;
	}
	
	public @NotNull ItemStack getStoreItem () {
		//出品中の在庫を見るためのチェスト
		ItemStack store = new ItemStack (Material.ENDER_CHEST);
		ItemMeta store_meta = store.getItemMeta ();
		store_meta.displayName (Component.text ("出品中の在庫",TextColor.color (255,255,0)));
		store.setItemMeta (store_meta);
		return store;
	}
    
    public @NotNull ItemStack getBuyHistoryItem () {
        //購入履歴表示用の本
        ItemStack buyHistory = new ItemStack (Material.KNOWLEDGE_BOOK);
        ItemMeta buyHistory_meta = buyHistory.getItemMeta ();
        buyHistory_meta.displayName (Component.text ("購入履歴を見る",TextColor.color (0,226,0)));
        buyHistory.setItemMeta (buyHistory_meta);
        return buyHistory;
    }
    
    public @NotNull ItemStack getSellHistoryItem () {
        //販売履歴表示用の本
        ItemStack sellHistory = new ItemStack (Material.ENCHANTED_BOOK);
        ItemMeta sellHistory_meta = sellHistory.getItemMeta ();
        sellHistory_meta.displayName (Component.text ("販売履歴を見る",TextColor.color (255,0,255)));
        sellHistory.setItemMeta (sellHistory_meta);
        return sellHistory;
    }
    
    public @NotNull ItemStack getTerminalItem () {
        //ターミナルに戻るためのネザースター
        ItemStack terminal = new ItemStack (Material.NETHER_STAR);
        ItemMeta terminal_meta = terminal.getItemMeta ();
        terminal_meta.displayName (Component.text ("ターミナルに戻る",TextColor.color (42,101,255)));
        terminal.setItemMeta (terminal_meta);
        return terminal;
    }
    
    public @NotNull ItemStack getNoticeYoursItem () {
        
        ItemStack yours = new ItemStack (Material.BARRIER);
        ItemMeta yours_meta = yours.getItemMeta ();
        yours_meta.displayName (Component.text ("このアイテムは購入できません。",TextColor.color (232,57,41)));
        ArrayList<Component> yours_lore = new ArrayList<> ();
        yours_lore.add (Component.text ("アイテムの出品を取り下げる場合は出品中の在庫から選択してください",TextColor.color (128,128,128)));
        yours_meta.lore (yours_lore);
        yours.setItemMeta (yours_meta);
        return yours;
    }
    
    public @NotNull ItemStack getNoticeNoEmptyItem () {
        
        ItemStack noEmpty = new ItemStack (Material.BARRIER);
        ItemMeta noEmpty_meta = noEmpty.getItemMeta ();
        noEmpty_meta.displayName (Component.text ("インベントリに空きスロットがありません",TextColor.color (232,57,41)));
        ArrayList<Component> noEmpty_lore = new ArrayList<> ();
        noEmpty_lore.add (Component.text ("購入する場合はインベントリを開けてください",TextColor.color (128,128,128)));
        noEmpty_meta.lore (noEmpty_lore);
        noEmpty.setItemMeta (noEmpty_meta);
        return noEmpty;
    }
    
    public @NotNull ItemStack getNoticeNoMoneyItem () {
        
        ItemStack noMoney = new ItemStack (Material.BARRIER);
        ItemMeta noMoney_meta = noMoney.getItemMeta ();
        noMoney_meta.displayName (Component.text ("購入するためのお金を持っていません",TextColor.color (232,57,41)));
        noMoney.setItemMeta (noMoney_meta);
        return noMoney;
    }
    
    public @NotNull ItemStack getCloseItem () {
        //ページを閉じることを知らせるバリアブロック
        ItemStack close = new ItemStack (Material.BARRIER);
        ItemMeta close_meta = close.getItemMeta ();
        close_meta.displayName (Component.text ("閉じる",TextColor.color (232,57,41)));
        ArrayList<Component> close_lore = new ArrayList<> ();
        close_lore.add (Component.text ("ページを閉じる",TextColor.color (128,128,128)));
        close_meta.lore (close_lore);
        close.setItemMeta (close_meta);
        return close;
    }

    public @NotNull ItemStack getNoDataGlassItem() {
        //データがないことを知らせるガラス
        ItemStack noDataGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta noDataGlass_meta = noDataGlass.getItemMeta();
        noDataGlass_meta.displayName(Component.text("ここには何もありません", TextColor.color(255, 255, 255)));
        ArrayList<Component> noDataGlass_lore = new ArrayList<>();
        noDataGlass_lore.add(Component.text("これは商品ではありません", TextColor.color(122, 147, 255)));
        noDataGlass_meta.lore(noDataGlass_lore);
        noDataGlass.setItemMeta(noDataGlass_meta);
        return noDataGlass;
    }

    public @NotNull ItemStack getBuyItem() {
        //物品購入所のボタンのエメラルド
        ItemStack buy = new ItemStack(Material.EMERALD);
        ItemMeta buy_meta = buy.getItemMeta();
        buy_meta.displayName(Component.text("物品購入所", TextColor.color(100, 255, 130)));
        ArrayList<Component> buy_lore = new ArrayList<>();
        buy_lore.add(Component.text("物品購入所へ移動", TextColor.color(128, 128, 128)));
        buy_meta.lore(buy_lore);
        buy.setItemMeta(buy_meta);

        return buy;
    }

    public @NotNull ItemStack getSellItem() {
        //物品販売所所のボタンの金インゴット
        ItemStack sell = new ItemStack(Material.GOLD_INGOT);
        ItemMeta sell_meta = sell.getItemMeta();
        sell_meta.displayName(Component.text("物品販売所", TextColor.color(251, 107, 255)));
        ArrayList<Component> sell_lore = new ArrayList<>();
        sell_lore.add(Component.text("物品販売所へ移動", TextColor.color(128, 128, 128)));
        sell_meta.lore(sell_lore);
        sell.setItemMeta(sell_meta);

        return sell;
    }

    public @NotNull ItemStack getSetItemGlassItem() {
        //アイテムをセットすることを伝えるガラス
        ItemStack setItemGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta setItem_meta = setItemGlass.getItemMeta();
        setItem_meta.displayName(Component.text("商品をセットしてください", TextColor.color(255, 255, 255)));
        ArrayList<Component> setItemGlass_lore = new ArrayList<>();
        setItemGlass_lore.add(Component.text("セットしたら黄緑の羊毛をクリックして決定してください", TextColor.color(45, 146, 255)));
        setItem_meta.lore(setItemGlass_lore);
        setItemGlass.setItemMeta(setItem_meta);
        return setItemGlass;
    }

    public @NotNull ItemStack getBlankGlassItem() {
        //アイテムをセットすることを伝えるガラス
        ItemStack blankGlass = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta blank_meta = blankGlass.getItemMeta();
        blank_meta.displayName(Component.text("ボタンを押して選択してください", TextColor.color(255, 255, 255)));
        blankGlass.setItemMeta(blank_meta);
        return blankGlass;
    }

    public @NotNull ItemStack getAcceptItem() {
        //決定用ボタンの羊毛
        ItemStack accept = new ItemStack(Material.LIME_WOOL);
        ItemMeta accept_meta = accept.getItemMeta();
        accept_meta.displayName(Component.text("決定", TextColor.color(0, 255, 0)));
        ArrayList<Component> accept_lore = new ArrayList<>();
        accept_lore.add(Component.text("決定する場合こちら", TextColor.color(204, 133, 0)));
        accept_meta.lore(accept_lore);
        accept.setItemMeta(accept_meta);
        return accept;
    }

    public @NotNull ItemStack getDenyItem() {
	    //拒否用ボタンの羊毛
	    ItemStack deny = new ItemStack (Material.RED_WOOL);
	    ItemMeta deny_meta = deny.getItemMeta ();
	    deny_meta.displayName (Component.text ("戻る",TextColor.color (255,0,0)));
	    ArrayList<Component> deny_lore = new ArrayList<> ();
	    deny_lore.add (Component.text ("戻る場合こちら",TextColor.color (178,34,34)));
	    deny_meta.lore (deny_lore);
	    deny.setItemMeta (deny_meta);
	    return deny;
    }
	
	public @NotNull ItemStack getSellerItem (@NotNull ItemStack Item,@NotNull String name,@NotNull String price,@NotNull String time,@NotNull String description) {
		ItemStack stock = Item.clone ();
		ItemMeta merchandise_meta = stock.getItemMeta ();
		ArrayList<Component> merchandise_lore = new ArrayList<> ();
		
		merchandise_lore.add (Component.text ("出品者 : ",TextColor.color (255,215,0))
				.append (Component.text (name,TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("金額   : ",TextColor.color (255,215,0))
				.append (Component.text (Long.valueOf (price).toString (),TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("期限   : ",TextColor.color (255,215,0))
				.append (Component.text (time,TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("説明   : ",TextColor.color (255,215,0))
				.append (Component.text (description,TextColor.color (255,255,255))));
		
		merchandise_meta.lore (merchandise_lore);
		stock.setItemMeta (merchandise_meta);
		return stock;
	}
	
	public @NotNull ItemStack getReturnedItem (@NotNull ItemStack Item,@NotNull String name,@NotNull String price,@NotNull String time,@NotNull String description) {
		ItemStack stock = Item.clone ();
		ItemMeta merchandise_meta = stock.getItemMeta ();
		ArrayList<Component> merchandise_lore = new ArrayList<> ();
		
		merchandise_lore.add (Component.text ("出品者       : ",TextColor.color (255,215,0))
				.append (Component.text (name,TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("金額         : ",TextColor.color (255,215,0))
				.append (Component.text (Long.valueOf (price).toString (),TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("返却された時間 : ",TextColor.color (255,215,0))
				.append (Component.text (time,TextColor.color (255,255,255))));
		
		merchandise_lore.add (Component.text ("説明         : ",TextColor.color (255,215,0))
				.append (Component.text (description,TextColor.color (255,255,255))));
		
		merchandise_meta.lore (merchandise_lore);
		stock.setItemMeta (merchandise_meta);
		return stock;
	}
	
}
