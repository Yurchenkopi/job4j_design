package ru.job4j.exercise.map;

import org.junit.Test;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class RomanToArabicConverterTest {

    @Test
    public void testConvertSimple() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        assertThat(converter.convert("X"), is(10));
    }

    @Test
    public void testConvertComplex() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        assertThat(converter.convert("MCMXCIV"), is(1994));
    }

    @Test
    public void testConvertSingleSymbol() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        assertThat(converter.convert("I"), is(1));
        assertThat(converter.convert("V"), is(5));
        assertThat(converter.convert("M"), is(1000));
    }

    @Test
    public void testConvertWithSubtractiveNotation() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        assertThat(converter.convert("IV"), is(4));
        assertThat(converter.convert("IX"), is(9));
        assertThat(converter.convert("XL"), is(40));
        assertThat(converter.convert("XC"), is(90));
        assertThat(converter.convert("CD"), is(400));
        assertThat(converter.convert("CM"), is(900));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertInvalidInput() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        converter.convert("IIII"); // Некорректное римское число
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConvertEmptyInput() {
        RomanToArabicConverter converter = new RomanToArabicConverter();
        converter.convert(""); // Пустая строка, невалидный ввод
    }
}
