package ru.job4j.ood.isp.menu;

import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;

public class TodoApp {
    public static final int ADD_TO_ROOT = 1;
    public static final int ADD_TO_PARENT = 2;
    public static final int TO_DO_ACTION = 3;
    public static final int VIEW_MENU = 4;

    public static final String MENU_SELECT = "Выберите меню";
    public static final String MENU_PARENT_NAME = "Введите название родительского меню";
    public static final String MENU_NAME = "Введите название меню";

    public static final String EXIT = "Конец работы";

    public static final ActionDelegate ACTION_1 = () -> System.out.println("Some action #1");
    public static final ActionDelegate ACTION_2 = () -> System.out.println("Some action #2");
    public static final ActionDelegate ACTION_3 = () -> System.out.println("Some action #3");

    public static final String MENU = """
                1 - Добавить элемент в корневое меню
                2 - Добавить элемент к родительскому элементы
                3 - Вызвать действие
                4 - Вывод меню.
            """;

    public static final String MENU_ACTION = """
                1 - ACTION_1
                2 - ACTION_2
                3 - ACTION_3
                """;

    public static void main(String[] args) throws IOException {
        start(new Scanner(System.in));
    }

    private static void start(Scanner sc) {
        boolean run = true;
        Menu menu = new SimpleMenu();

        while (run) {
            System.out.println(MENU);
            System.out.println(MENU_SELECT);
            int userChoice = Integer.parseInt(sc.nextLine());
            System.out.println(userChoice);
            if (ADD_TO_ROOT == userChoice) {
                System.out.println(MENU_NAME);
                String name = sc.nextLine();
                ActionDelegate userAction = chooseAction(sc);
                menu.add(menu.ROOT, name, userAction);
            } else if (ADD_TO_PARENT == userChoice) {
                System.out.println(MENU_PARENT_NAME);
                String parentName = sc.nextLine();
                System.out.println(MENU_NAME);
                String childName = sc.nextLine();
                ActionDelegate userAction = chooseAction(sc);
                menu.add(parentName, childName, userAction);
            } else if (TO_DO_ACTION == userChoice) {
                System.out.println(MENU_NAME);
                String name = sc.nextLine();
                Optional<Menu.MenuItemInfo> item = menu.select(name);
                item.ifPresent(menuItemInfo -> menuItemInfo.getActionDelegate().delegate());
            } else if (VIEW_MENU == userChoice) {
                MenuPrinter printer = new SimpleMenuPrinter();
                printer.print(menu);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    private static ActionDelegate chooseAction(Scanner sc) {
        ActionDelegate rsl;
        System.out.println(MENU_ACTION);
        System.out.println(MENU_SELECT);
        int userChoice = Integer.parseInt(sc.nextLine());
        if (userChoice == 1) {
            rsl = ACTION_1;
        } else if (userChoice == 2) {
            rsl = ACTION_2;
        } else if (userChoice == 3) {
            rsl = ACTION_3;
        } else {
            rsl = null;
        }
        return rsl;
    }

}
