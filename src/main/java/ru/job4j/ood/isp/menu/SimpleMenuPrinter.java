package ru.job4j.ood.isp.menu;

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
}
