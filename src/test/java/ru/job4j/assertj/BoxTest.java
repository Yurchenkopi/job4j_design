package ru.job4j.assertj;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisCube() {
        Box box = new Box(8, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Cube")
                .startsWithIgnoringCase("cu")
                .containsIgnoringCase("UB");
    }

    @Test
    void checkNumVertices() {
        Box box = new Box(8, 10);
        assertThat(box.getNumberOfVertices()).isEqualTo(8)
                .isEven()
                .isGreaterThan(4);
    }

    @Test
    void whenVertexIs9ThenUnknownObject() {
        Box box = new Box(9, 10);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void whenEdgeIsMinus2ThenUnknownObject() {
        Box box = new Box(4, -2);
        assertThat(box.whatsThis()).isEqualTo("Unknown object");
    }

    @Test
    void isFigureExist() {
        Box box = new Box(4, 10);
        assertThat(box.isExist()).isTrue();
    }

    @Test
    void checkSurfaceArea() {
        Box box = new Box(4, 10);
        assertThat(box.getArea()).isEqualTo(173.3d, withPrecision(0.1d))
                .isCloseTo(169.5, withPrecision(3.8d))
                .isCloseTo(169.5, Percentage.withPercentage(3))
                .isLessThan(173.3d);
    }

}