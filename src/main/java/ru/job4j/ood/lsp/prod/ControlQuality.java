package ru.job4j.ood.lsp.prod;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
        return 100 - (int) ((
                (double) Math.round(ChronoUnit.DAYS.between(food.getCreateDate(), LocalDate.now()))
                        / (double) Math.round(ChronoUnit.DAYS.between(food.getCreateDate(), food.getExpiryDate()))
        ) * 100.0);
    }

    public static void main(String[] args) {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(5, ChronoUnit.DAYS),
                        LocalDate.now().plus(5, ChronoUnit.DAYS),
                        100,
                        0),
                new Food(
                        "lemon",
                        LocalDate.now().minus(7, ChronoUnit.DAYS),
                        LocalDate.now().plus(3, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "orange",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0)
        );
        ControlQuality cq = new ControlQuality();
        cq.filterAll(data);
        System.out.println(cq.getShop());

    }
}
