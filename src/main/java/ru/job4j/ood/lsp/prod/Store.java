package ru.job4j.ood.lsp.prod;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T> {
    void add(T product);

    List<T> findBy(Predicate<T> filter);
}
