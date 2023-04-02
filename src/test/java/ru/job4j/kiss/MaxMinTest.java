package ru.job4j.kiss;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MaxMinTest {

    @Test
    void whenListOfIntAndMax() {
        List<Integer> data = List.of(4, 2, 123, 34, 12, 546, 6, 123, 1, 6, 10);
        int rsl = new MaxMin().max(data, Comparator.naturalOrder());
        int expected = 546;
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void whenListOfIntAndMin() {
        List<Integer> data = List.of(4, 2, 123, 34, 12, 546, 6, 123, 1, 6, 0);
        int rsl = new MaxMin().min(data, Comparator.naturalOrder());
        int expected = 0;
        assertThat(rsl).isEqualTo(expected);
    }

    @Test
    void whenListOfUsersByAscAndMax() {
        record User(int age, String name) { }
        List<User> data = List.of(
                new User(14, "Ivan"),
                new User(18, "Dasha"),
                new User(1, "Olya"),
                new User(34, "Roman"),
                new User(24, "Sonya"),
                new User(40, "Tolik")
        );
        User rsl = new MaxMin().max(
                data,
                (u1, u2) -> Integer.compare(u1.age(), u2.age())
        );
        String expected = "Tolik";
        assertThat(rsl.name).isEqualTo(expected);
    }

    @Test
    void whenListOfUsersByAscAndMin() {
        record User(int age, String name) { }
        List<User> data = List.of(
                new User(14, "Ivan"),
                new User(18, "Dasha"),
                new User(1, "Olya"),
                new User(34, "Roman"),
                new User(24, "Sonya"),
                new User(40, "Tolik")
        );
        User rsl = new MaxMin().min(
                data,
                (u1, u2) -> Integer.compare(u1.age(), u2.age())
        );
        String expected = "Olya";
        assertThat(rsl.name).isEqualTo(expected);
    }

    @Test
    void whenEmptyListAndMax() {
        List<Integer> data = List.of();
        assertThat(new MaxMin().max(data, Comparator.naturalOrder())).isNull();
    }

    @Test
    void whenEmptyListAndMin() {
        List<Integer> data = List.of();
        assertThat(new MaxMin().min(data, Comparator.naturalOrder())).isNull();
    }

    @Test
    void whenInputValueIsNull() {
        List<Integer> data = null;
        assertThat(new MaxMin().min(data, Comparator.naturalOrder())).isNull();
    }
}