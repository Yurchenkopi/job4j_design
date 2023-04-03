package ru.job4j.ood.tdd;

import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class Cinema3D implements Cinema {

    private final static int TOTAL_PLACES = 3;

    @Override
    public int getPlacesAmount() {
        return TOTAL_PLACES;
    }

    @Override
    public Ticket buy(Account account, int row, int placeNum, Calendar date) {
        return null;
    }

    @Override
    public boolean add(Session session) {
        return false;
    }

    @Override
    public List<Session> find(Predicate<Session> condition) {
        return null;
    }
}
