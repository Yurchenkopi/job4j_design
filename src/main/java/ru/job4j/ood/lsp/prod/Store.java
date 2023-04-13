package ru.job4j.ood.lsp.prod;

import java.util.List;
import java.util.function.Predicate;

public interface Store<T>  {

    void add(T product);

    void addAll(List<T> products);

    List<T> findBy(Predicate<T> filter);

    List<T> findAll();

    boolean remove(T product);
}
