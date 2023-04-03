package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public interface Cinema {

    int getPlacesAmount();
    Ticket buy(Account account, int row, int placeNum, Calendar date);

    boolean add(Session session);

    List<Session> find(Predicate<Session> condition);
}
