package ru.job4j.kiss;

import java.util.Comparator;
import java.util.List;

public class MaxMin {
    public <T> T max(List<T> value, Comparator<T> comparator) {
        T rsl = null;
        if (value != null && !value.isEmpty()) {
            rsl = value.get(0);
            for (T v : value) {
                rsl = max(rsl, v, comparator);
            }
        }
        return rsl;
    }

    public <T> T min(List<T> value, Comparator<T> comparator) {
        T rsl = null;
        if (value != null && !value.isEmpty()) {
            rsl = value.get(0);
            for (T v : value) {
                rsl = min(rsl, v, comparator);
            }
        }
            return rsl;
    }

    private <T> T  max(T val1, T val2, Comparator<T> comparator) {
        return  comparator.compare(val1, val2) > 0 ? val1 : val2;
    }

    private <T> T  min(T val1, T val2, Comparator<T> comparator) {
        return  comparator.compare(val1, val2) > 0 ? val2 : val1;
    }
}