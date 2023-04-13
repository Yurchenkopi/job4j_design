package ru.job4j.ood.lsp.prod;

public class Shop extends AbstractStore<Food>  {
    private String category;

    public Shop(String name, String category) {
        super(name);
        this.category = category;
    }

    @Override
    public void add(Food food) {
        int expirationPercentage = food.expirationDefine();
        if (expirationPercentage >= 25 && expirationPercentage < 75) {
            super.store.add(food);
        } else if (expirationPercentage >= 75 && expirationPercentage < 100) {
            food.setPrice(food.getPrice() * (1 -  food.getDiscount() / 100));
            super.store.add(food);
        }
    }
}
