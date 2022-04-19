package ru.job4j.it;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ListUtilsTest {

    @Test
    public void whenAddBefore() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 1, 2);
        assertThat(input, is(Arrays.asList(1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddBeforeWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 3, 2);
    }

    @Test
    public void whenAddAfterLast() {
        List<Integer> input = new ArrayList<>(Arrays.asList(0, 1, 2));
        ListUtils.addAfter(input, 2, 3);
        assertThat(input, is(Arrays.asList(0, 1, 2, 3)));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void whenAddAfterWithInvalidIndex() {
        List<Integer> input = new ArrayList<>(Arrays.asList(1, 3));
        ListUtils.addBefore(input, 5, 1);
    }

    @Test
    public void whenRemoveWithPredicate1() {
        List<String> input = new ArrayList<>(Arrays.asList("Apple", "Orange", "Pineapple", "Grape", "Pear"));
        ListUtils.removeIf(input, s -> s.startsWith("P"));
        assertThat(input, is(Arrays.asList("Apple", "Orange", "Grape")));
    }

    @Test
    public void whenRemoveWithPredicate2() {
        List<String> input = new ArrayList<>(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear"));
        ListUtils.removeIf(input, s -> Character.isUpperCase(s.charAt(0)));
        assertThat(input, is(Arrays.asList("apple", "pineapple", "pear")));
    }

    @Test
    public void whenReplaceWithPredicate() {
        List<String> input = new ArrayList<>(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear"));
        ListUtils.replaceIf(input, s -> Character.isUpperCase(s.charAt(0)), "TEST");
        assertThat(input, is(Arrays.asList("apple", "TEST", "pineapple", "TEST", "pear")));
    }

    @Test
    public void whenRemoveAll1() {
        List<String> input = new ArrayList<>(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear"));
        List<String> elements = new ArrayList<>(Arrays.asList("apple", "pineapple", "pear"));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList("Orange", "Grape")));
    }

    @Test
    public void whenRemoveAll2() {
        List<String> input = new ArrayList<>(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear"));
        List<String> elements = new ArrayList<>(Arrays.asList("apple", "pineapple", "pear", "Banana", "Apricot"));
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList("Orange", "Grape")));
    }

    @Test
    public void whenRemoveAllAndNoElements() {
        List<String> input = new ArrayList<>(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear"));
        List<String> elements = new ArrayList<>();
        ListUtils.removeAll(input, elements);
        assertThat(input, is(Arrays.asList("apple", "Orange", "pineapple", "Grape", "pear")));
    }
}