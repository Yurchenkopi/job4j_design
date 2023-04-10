package ru.job4j.ood.lsp.prod;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore implements Store<Food> {
    private String name;
    private List<Food> store;

    public AbstractStore(String name) {
        this.name = name;
    }

    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public List<Food> findBy(Predicate<Food> condition) {
        return store.stream()
                .filter(condition)
                .toList();
    }
}
