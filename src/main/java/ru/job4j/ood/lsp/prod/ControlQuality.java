package ru.job4j.ood.lsp.prod;

import java.util.List;

public class ControlQuality {

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

    public void filterAll(List<Food> products) {
        for (Food f : products) {
            filter(f);
        }
    }

    private void filter(Food food) {
        warehouse.add(food);
        shop.add(food);
        trash.add(food);
    }
}
