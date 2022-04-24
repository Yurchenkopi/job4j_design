package ru.job4j.collection.hash;

import java.util.Objects;

public class MyCalendar {
    private final int day;
    private final int month;
    private final int year;

    public MyCalendar(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getBirthday() {
        return day + month + year;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MyCalendar that = (MyCalendar) o;
        return day == that.day && month == that.month && year == that.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(day, month, year);
    }
}
