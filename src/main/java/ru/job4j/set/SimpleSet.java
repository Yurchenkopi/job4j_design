package ru.job4j.set;

import ru.job4j.list.SimpleArrayList;

import java.util.Iterator;
import java.util.Objects;

public class SimpleSet<T> implements Set<T> {

    private SimpleArrayList<T> set = new SimpleArrayList<>(0);

    @Override
    public boolean add(T value) {
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (Objects.equals(item, value)) {
                return false;
            }
        }
        set.add(value);
        return true;
    }

    @Override
    public boolean contains(T value) {
        Iterator<T> it = set.iterator();
        while (it.hasNext()) {
            T item = it.next();
            if (Objects.equals(item, value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return set.iterator();
    }
}