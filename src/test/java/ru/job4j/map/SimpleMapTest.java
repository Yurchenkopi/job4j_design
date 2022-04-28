package ru.job4j.map;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class SimpleMapTest {

    @Test
    public void whenPutThenTrue() {
       SimpleMap<Integer, String> map = new SimpleMap<>();
       assertTrue(map.put(1, "Apple"));
       assertTrue(map.put(2, "Orange"));
       assertFalse(map.put(2, "Orange"));
    }

    @Test
    public void whenGet() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Apple");
        map.put(2, "Orange");
        map.put(3, "Grape");
        assertThat(map.get(1), is("Apple"));
        assertThat(map.get(2), is("Orange"));
        assertThat(map.get(3), is("Grape"));
    }

    @Test
    public void whenGetIterator() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Apple");
        map.put(5, "Orange");
        map.put(10, "Grape");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
        assertFalse(it.hasNext());
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGetIteratorFromEmptyMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        Iterator<Integer> it = map.iterator();
        it.next();
    }

    @Test (expected = NoSuchElementException.class)
    public void whenGetIteratorAndNoMoreElements() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Apple");
        map.put(5, "Orange");
        map.put(10, "Grape");
        Iterator<Integer> it = map.iterator();
        it.next();
        it.next();
        it.next();
        it.next();
    }

    @Test (expected = ConcurrentModificationException.class)
    public void whenGetIteratorAndTryChangingTheMap() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Apple");
        map.put(5, "Orange");
        map.put(10, "Grape");
        Iterator<Integer> it = map.iterator();
        it.next();
        map.put(11, "Banana");
        it.next();
    }

    @Test
    public void whenExpand() {
        SimpleMap<Integer, String> map = new SimpleMap<>();
        map.put(1, "Apple");
        map.put(2, "Orange");
        map.put(3, "Grape");
        map.put(4, "Apple");
        map.put(5, "Orange");
        map.put(6, "Grape");
        map.put(7, "Apple");
        map.put(8, "Orange");
        assertThat(map.capacity(), is(8));
        map.put(9, "Grape");
        assertThat(map.capacity(), is(16));
    }
}