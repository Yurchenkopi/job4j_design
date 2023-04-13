package ru.job4j.ood.lsp.prod;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AbstractStoreTest {

    @Test
    public void whenAddAllToStoreAndFindByCondition() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(5, ChronoUnit.DAYS),
                        LocalDate.now().plus(5, ChronoUnit.DAYS),
                        100,
                        0),
                new Food(
                        "lemon",
                        LocalDate.now().minus(1, ChronoUnit.DAYS),
                        LocalDate.now().plus(9, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "orange",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0)
        );
        AbstractStore<Food> store = new Shop("megashop", "products");
        store.addAll(data);
        String expected = "apples";
        assertThat(store.findBy(f -> "apples".equals(f.getName())).get(0).getName()).isEqualTo(expected);
    }

    @Test
    public void whenAddAllToStoreAndFindAll() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(5, ChronoUnit.DAYS),
                        LocalDate.now().plus(5, ChronoUnit.DAYS),
                        100,
                        0),
                new Food(
                        "lemon",
                        LocalDate.now().minus(1, ChronoUnit.DAYS),
                        LocalDate.now().plus(9, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "orange",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0)
        );
        AbstractStore<Food> store = new Shop("megashop", "products");
        store.addAll(data);
        int expected = 4;
        assertThat(store.findAll().size()).isEqualTo(expected);
    }

    @Test
    public void whenAddAllToStoreAndRemoveOneProduct() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(5, ChronoUnit.DAYS),
                        LocalDate.now().plus(5, ChronoUnit.DAYS),
                        100,
                        0),
                new Food(
                        "lemon",
                        LocalDate.now().minus(1, ChronoUnit.DAYS),
                        LocalDate.now().plus(9, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "orange",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(6, ChronoUnit.DAYS),
                        LocalDate.now().plus(4, ChronoUnit.DAYS),
                        50,
                        0)
        );
        AbstractStore<Food> store = new Shop("megashop", "products");
        store.addAll(data);
        store.remove(new Food(
                "banana",
                LocalDate.now().minus(6, ChronoUnit.DAYS),
                LocalDate.now().plus(4, ChronoUnit.DAYS),
                50,
                0));
        int expected = 3;
        assertThat(store.findAll().size()).isEqualTo(expected);
    }
}