package ru.job4j.ood.lsp.prod;

import java.util.List;

public interface StoreLogic {

    void filter(Food food);

    void filterAll(List<Food> products);

    void resort();

    void clearStore();

}
