package ru.job4j.ood.lsp.prod;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
        int expected = 100;
        ControlQuality cq = new ControlQuality();
        assertThat(cq.expirationDefine(apples)).isEqualTo(expected);
    }

    @Test
    public void whenRelativeExpirationDateDefineAndTrash() {
        Food apples = new Food(
                "apples",
                LocalDate.of(1980, 4, 30),
                LocalDate.now(),
                100,
                0);
        int expected = 0;
        ControlQuality cq = new ControlQuality();
        assertThat(cq.expirationDefine(apples)).isEqualTo(expected);
    }

}