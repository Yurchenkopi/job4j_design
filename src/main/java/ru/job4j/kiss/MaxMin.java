package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        return find(
                value,
                (o1, o2) -> comparator.compare(o2, o1) > 0
        );
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        return find(
                value,
                (o1, o2) -> comparator.compare(o1, o2) > 0
        );
    }

    private <T> T find(List<T> value, BiPredicate<T, T> condition) {
        T rsl = null;
        if (value != null && !value.isEmpty()) {
            rsl = value.get(0);
            for (T v : value) {
                if (condition.test(rsl, v)) {
                    rsl = v;
                }
            }
        }
        return rsl;
    }
}