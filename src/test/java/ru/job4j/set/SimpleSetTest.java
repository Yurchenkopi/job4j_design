package ru.job4j.set;

import org.junit.Test;

import static org.junit.Assert.*;

public class SimpleSetTest {

    @Test
    public void whenAddNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.contains(1));
        assertFalse(set.add(1));
    }

    @Test
    public void whenContainOfNullSet() {
        Set<Integer> set = new SimpleSet<>();
        assertFalse(set.contains(1));
    }

    @Test
    public void whenMultiAddAndMultiContainsNonNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(1));
        assertTrue(set.add(2));
        assertTrue(set.add(3));
        assertTrue(set.add(4));
        assertTrue(set.contains(1));
        assertTrue(set.contains(3));
        assertTrue(set.contains(4));
        assertFalse(set.contains(10));
        assertFalse(set.add(1));
        assertTrue(set.add(5));
        assertTrue(set.contains(5));
    }

    @Test
    public void whenAddNull() {
        Set<Integer> set = new SimpleSet<>();
        assertTrue(set.add(null));
        assertTrue(set.contains(null));
        assertFalse(set.add(null));
    }

}