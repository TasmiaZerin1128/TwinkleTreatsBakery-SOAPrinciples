package com.wstutorial.ws;

import com.wstutorial.ws.commonservice.AcknowledgementCode;
import com.wstutorial.ws.itemservice.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemRepository {

    List<Item> Items = new ArrayList<>();

    public ItemRepository() {
        Item item1 = new Item();
        item1.setId(1001);
        item1.setItemName("Brown Bread");
        item1.setPrice(50);
        item1.setStock(128);

        Item item2 = new Item();
        item2.setId(1001);
        item2.setItemName("Brown Bread");
        item2.setPrice(50);
        item2.setStock(128);

        Items.add(item1);
        Items.add(item2);
    }
    public List<Item> getAllItems() {
        return Items;
    }

    public Item getItemById(Long id) {
        for(Item r: Items) {
            if(r.getId() == id) {
                return r;
            }
        }
        System.out.println("Item not found with id: " + id);
        return null;
    }

    public AcknowledgementCode deleteItemById(Long id) {
        for(Item r: Items) {
            if(r.getId() == id) {
                Items.remove(r);
                return AcknowledgementCode.DELETED;
            }
        }
        System.out.println("Item not found with id: " + id + ", Delete aborted");
        return AcknowledgementCode.FAILED;
    }

    public AcknowledgementCode updateItemById(Long id, Item Item) {
        if (id == null || Item == null) {
            System.out.println("Invalid input: ID or Item is null");
            return AcknowledgementCode.FAILED;
        }

        for (int i = 0; i < Items.size(); i++) {
            Long ItemId = Items.get(i).getId();
            if (ItemId.equals(id)) {
                Items.set(i, Item);
                return AcknowledgementCode.UPDATED;
            }
        }
        System.out.println("Item not found with id: " + id + ", Update aborted");
        return AcknowledgementCode.FAILED;
    }


    public void createNewItem(Item Item) {
        Item.setId(Items.size()+1001);
        Items.add(Item);
    }

}
