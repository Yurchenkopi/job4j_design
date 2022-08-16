package ru.job4j.assertj;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class NameLoadTest {
    @Test
    void checkEmpty() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::getMap)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("no data");
    }

    @Test
    void checkParseWhenNoData() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(nameLoad::parse)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("array is empty");
    }

    @Test
    void whenValidateAndNoDelimiter() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Ivan=Ivanov", "Vasya=Vasiliev", "PetyaPetrov"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("the symbol \"=\"");
    }

    @Test
    void whenValidateAndNoKey() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("=Ivanov", "Vasya=Vasiliev", "Petya=Petrov"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("key");
    }

    @Test
    void whenValidateAndNoValue() {
        NameLoad nameLoad = new NameLoad();
        assertThatThrownBy(() -> nameLoad.parse("Ivan=Ivanov", "Vasya=", "Petya=Petrov"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("value");
    }
}