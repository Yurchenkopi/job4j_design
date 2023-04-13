package ru.job4j.ood.lsp.prod;

import java.util.List;

public class Trash extends AbstractStore<Food>  {
    public Trash(String name) {
        super(name);
    }

    @Override
    public void add(Food food) {
        if (food.expirationDefine() >= 100) {
            super.store.add(food);
        }
    }
}
