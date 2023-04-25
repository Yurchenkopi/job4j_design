package ru.job4j.ood.isp.menu;

import java.util.concurrent.atomic.AtomicInteger;

public class SimpleMenuPrinter implements MenuPrinter {
    @Override
    public void print(Menu menu) {
        int index = 0;
        for (Menu.MenuItemInfo m : menu) {
            if (m.getNumber().length() != 2) {
                index++;
            } else {
                index = 0;
            }
            System.out.println("-".repeat(4 * index) + m.getNumber() + m.getName());
        }
    }

    public static void main(String[] args) {
        final ActionDelegate STUB_ACTION = System.out::println;
        Menu menu = new SimpleMenu();
        menu.add(Menu.ROOT, "Сходить в магазин", STUB_ACTION);
        menu.add(Menu.ROOT, "Покормить собаку", STUB_ACTION);
        menu.add("Сходить в магазин", "Купить продукты", STUB_ACTION);
        menu.add("Купить продукты", "Купить хлеб", STUB_ACTION);
        menu.add("Купить продукты", "Купить молоко", STUB_ACTION);
        SimpleMenuPrinter smp = new SimpleMenuPrinter();
        smp.print(menu);
    }
}
