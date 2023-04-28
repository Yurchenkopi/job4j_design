package ru.job4j.ood.lsp.prod;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractStore<Food> implements Store<Food> {
    private final String name;
    private List<Food> store = new ArrayList<>();

    public AbstractStore(String name) {
        this.name = name;
    }

    @Override
    public void add(Food food) {
        store.add(food);
    }

    @Override
    public void addAll(List<Food> products) {
        for (Food f : products) {
            store.add(f);
        }
    }

    @Override
    public List<Food> findBy(Predicate<Food> condition) {
        return store.stream()
                .filter(condition)
                .toList();
    }

    @Override
    public List<Food> findAll() {
        return store;
    }

    @Override
    public boolean remove(Food product) {
        return store.remove(product);
    }

    @Override
    public void removeAll() {
        store = new ArrayList<>();
    }
}
