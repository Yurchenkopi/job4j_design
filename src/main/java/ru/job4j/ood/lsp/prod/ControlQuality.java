package ru.job4j.ood.lsp.prod;

import java.util.List;

public class ControlQuality {

    private void moveAll(AbstractStore<Food> origStore, AbstractStore<Food> distStore) {
        List<Food> data = origStore.findAll();
        for (Food f : data) {
            distStore.add(f);
        }
    }

    private void move(AbstractStore<Food> origStore, Food food) {
        origStore.add(food);
    }

    private boolean remove(AbstractStore<Food> origStore, Food food) {
        return origStore.remove(food);
    }
}
