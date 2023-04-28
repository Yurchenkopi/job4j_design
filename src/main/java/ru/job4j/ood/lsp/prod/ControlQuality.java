package ru.job4j.ood.lsp.prod;

import java.util.ArrayList;
import java.util.List;

public class ControlQuality implements StoreLogic {

    private final AbstractStore<Food> warehouse = new Warehouse("basicwarehouse", 1_000_000);

    private final AbstractStore<Food> shop = new Shop("megashop", "supermarket");

    private final AbstractStore<Food> trash = new Trash("megatrash");

    public AbstractStore<Food> getWarehouse() {
        return warehouse;
    }

    public AbstractStore<Food> getShop() {
        return shop;
    }

    public AbstractStore<Food> getTrash() {
        return trash;
    }

    @Override
    public void filter(Food food) {
        warehouse.add(food);
        shop.add(food);
        trash.add(food);
    }

    @Override
    public void filterAll(List<Food> products) {
        for (Food f : products) {
            filter(f);
        }
    }

    @Override
    public void resort() {
        List<Food> tempData = new ArrayList<>();
        tempData.addAll(warehouse.findAll());
        tempData.addAll(shop.findAll());
        tempData.addAll(trash.findAll());
        clearStore();
        filterAll(tempData);
    }

    @Override
    public void clearStore() {
        warehouse.removeAll();
        shop.removeAll();
        trash.removeAll();
    }
}
