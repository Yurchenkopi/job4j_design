package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;

    private int capacity = 8;

    private int count = 0;

    private int modCount = 0;

    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int index = indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            count++;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    public int capacity() {
        return capacity;
    }

    private int hash(int hashCode) {
        return hashCode;
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity * 2;
        MapEntry<K, V>[] temp = new MapEntry[capacity];
        for (MapEntry<K, V> map : table) {
            if (map != null) {
                K key = map.key;
                V value = map.value;
                int index = indexFor(hash(map.key.hashCode()));
                temp[index] = new MapEntry<>(key, value);
            }
        }
        table = temp;
    }

    @Override
    public V get(K key) {
        int index = indexFor(hash(key.hashCode()));
        return table[index] != null && key.equals(table[index].key) ? table[index].value : null;
    }

    @Override
    public boolean remove(K key) {
        int index = indexFor(hash(key.hashCode()));
        if (table[index] != null && key.equals(table[index].key)) {
            table[index].key = null;
            table[index].value = null;
        }
        return table[index] != null && table[index].key == null;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private final int expectedModCount = modCount;
            private int index = 0;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                while (index != capacity && table[index] == null) {
                    index++;
                }
                return index != capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
