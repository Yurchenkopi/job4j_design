package ru.job4j.cache.menu;

import ru.job4j.cache.AbstractCache;
import ru.job4j.cache.DirFileCache;
import ru.job4j.io.Search;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    public static final int CACHING_DIR = 1;
    public static final int LOAD = 2;
    public static final int GET = 3;

    public static final String MENU_SELECT = "Выберите меню";
    public static final String MENU_LOAD_DONE = "Тестовые файлы внесены в кэш";
    public static final String MENU_LOAD_FILE = "Введите имя текстового файла";
    public static final String MENU_LOAD_DIR = "Введите имя кэшируемой директории";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                1 - Указать кэшируемую директорию
                2 - Загрузить сожержимое файла в кэш
                3 - Получить содержимое файла из кэш
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        start(new Scanner(System.in));
    }

    private static void start(Scanner sc) throws IOException {
        boolean run = true;
        while (run) {
            System.out.println(MENU);
            System.out.println(MENU_SELECT);
            int userChoice = Integer.parseInt(sc.nextLine());
            System.out.println(userChoice);
            if (CACHING_DIR == userChoice) {
                System.out.println(MENU_LOAD_DIR);
                String dir = sc.nextLine();
                DirFileCache dfCache = new DirFileCache(dir);



            } else if (LOAD == userChoice) {
                System.out.println(MENU_LOAD_FILE);
                String text = sc.nextLine();
                System.out.println(MENU_LOAD_DONE);
            } else if (GET == userChoice) {
                System.out.println(MENU_LOAD_FILE);
                String text = sc.nextLine();
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }
}
