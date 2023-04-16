package ru.job4j.ood.lsp.parking;

import java.util.List;
import java.util.function.Predicate;

public interface ParkingService<T> {
    void init();
    boolean add(T vehicle);

    boolean add(T vehicle, Predicate<T> condition);

    void addAll(List<T> vehicle, Predicate<T> condition);

    int findFreePlaces(int n);

    List<Integer> findBy(Predicate<T> condition);

    List<Integer> findAllOccupied();

    void stop(T vehicle);

}
