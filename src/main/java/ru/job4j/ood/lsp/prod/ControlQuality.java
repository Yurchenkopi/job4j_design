package ru.job4j.ood.lsp.prod;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ControlQuality {

    private AbstractStore<Food> warehouse = new Warehouse("basicwarehouse", 1_000_000);
    private AbstractStore<Food> shop = new Shop("megashop", "supermarket");
    private AbstractStore<Food> trash = new Trash("megatrash");

    private void moveAll(AbstractStore<Food> origStore, AbstractStore<Food> distStore) {
        List<Food> data = origStore.findAll();
        for (Food f : data) {
            distStore.add(f);
            origStore.remove(f);
        }
    }

    private void filter(Food food) {
        int relativeExpDate = expirationDefine(food);
        if (relativeExpDate < 25) {
            warehouse.add(food);
        } else if (relativeExpDate < 75) {
            shop.add(food);
        } else if (relativeExpDate < 100) {
            food.setPrice(food.getPrice() * (1 -  food.getDiscount() / 100));
            shop.add(food);
        } else {
            trash.add(food);
        }
    }

    public int expirationDefine(Food food) {
        return (int) ((
                (double) Math.round(ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now()))
                / (double) Math.round(ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate()))
        ) * 100.0);
    }
}
