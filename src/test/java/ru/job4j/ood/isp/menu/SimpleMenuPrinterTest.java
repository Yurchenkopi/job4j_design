package ru.job4j.ood.isp.menu;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

public class SimpleMenuPrinterTest {

    public static final ActionDelegate STUB_ACTION = null;

    @Test
    public void whenTestPrint()  {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        MenuPrinter simplePrinter = new SimpleMenuPrinter();
        simplePrinter.print(menu);
        String expected =
                "1.Сходить в магазин" + System.lineSeparator()
                + "----1.1.Купить продукты" + System.lineSeparator()
                + "--------1.1.1.Купить хлеб" + System.lineSeparator()
                + "------------1.1.2.Купить молоко" + System.lineSeparator()
                + "2.Покормить собаку" + System.lineSeparator();
        assertThat(out.toString()).isEqualTo(expected);
    }
}