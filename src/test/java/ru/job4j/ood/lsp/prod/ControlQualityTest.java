package ru.job4j.ood.lsp.prod;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ControlQualityTest {

    @Test
    public void whenRelativeExpirationDateDefineAndFresh() {
        Food apples = new Food(
                "apples",
                LocalDate.now(),
                LocalDate.of(2100, 4, 30),
                100,
                0);
        int expected = 0;
        assertThat(apples.expirationDefine()).isEqualTo(expected);
    }

    @Test
    public void whenRelativeExpirationDateDefineAndTrash() {
        Food apples = new Food(
                "apples",
                LocalDate.of(1980, 4, 30),
                LocalDate.now(),
                100,
                0);
        int expected = 100;
        assertThat(apples.expirationDefine()).isEqualTo(expected);
    }

    @Test
    public void whenRelativeExpirationDateDefineThenFiftyPercent() {
        Food apples = new Food(
                "apples",
                LocalDate.now().minus(10, ChronoUnit.DAYS),
                LocalDate.now().plus(10, ChronoUnit.DAYS),
                100,
                0);
        int expected = 50;
        assertThat(apples.expirationDefine()).isEqualTo(expected);
    }

    @Test
    public void whenFilterProductsAndMoveToShop() {
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
        int expected = 3;
        ControlQuality cq = new ControlQuality();
        cq.filterAll(data);
        assertThat(cq.getShop().findAll().size()).isEqualTo(expected);
    }

    @Test
    public void whenFilterProductsAndMoveToWarehouse() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(2, ChronoUnit.DAYS),
                        LocalDate.now().plus(8, ChronoUnit.DAYS),
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
                        LocalDate.now().minus(0, ChronoUnit.DAYS),
                        LocalDate.now().plus(10, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(4, ChronoUnit.DAYS),
                        LocalDate.now().plus(6, ChronoUnit.DAYS),
                        50,
                        0)
        );
        int expected = 3;
        ControlQuality cq = new ControlQuality();
        cq.filterAll(data);
        assertThat(cq.getWarehouse().findAll().size()).isEqualTo(expected);
    }

    @Test
    public void whenFilterProductsAndMoveToTrash() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(10, ChronoUnit.DAYS),
                        LocalDate.now().plus(0, ChronoUnit.DAYS),
                        100,
                        0),
                new Food(
                        "lemon",
                        LocalDate.now().minus(10, ChronoUnit.DAYS),
                        LocalDate.now().plus(0, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "orange",
                        LocalDate.now().minus(10, ChronoUnit.DAYS),
                        LocalDate.now().plus(0, ChronoUnit.DAYS),
                        50,
                        0),
                new Food(
                        "banana",
                        LocalDate.now().minus(1, ChronoUnit.DAYS),
                        LocalDate.now().plus(9, ChronoUnit.DAYS),
                        50,
                        0)
        );
        int expected = 3;
        ControlQuality cq = new ControlQuality();
        cq.filterAll(data);
        assertThat(cq.getTrash().findAll().size()).isEqualTo(expected);
    }

    @Test
    public void whenFilterProductsAndMoveToShopWithDiscount() {
        List<Food> data = List.of(
                new Food(
                        "apples",
                        LocalDate.now().minus(8, ChronoUnit.DAYS),
                        LocalDate.now().plus(2, ChronoUnit.DAYS),
                        100,
                        30)
        );
        int expected = 70;
        ControlQuality cq = new ControlQuality();
        cq.filterAll(data);
        assertThat(cq.getShop().findAll().get(0).getPrice()).isEqualTo(expected);
    }

}