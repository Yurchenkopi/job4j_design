package ru.job4j.cache.menu;


import ru.job4j.cache.DirFileCache;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Emulator {
    public static final int CACHING_DIR = 1;
    public static final int LOAD = 2;
    public static final int CACHE_PREVIEW = 3;
    public static final int GET = 4;

    public static final String MENU_SELECT = "Выберите меню";
    public static final String MENU_LOAD_DONE = "Тестовые файлы внесены в кэш";
    public static final String MENU_LOAD_FILE = "Введите имя текстового файла";
    public static final String MENU_LOAD_DIR = "Введите имя кэшируемой директории";

    public static final String MENU_DIR_ERROR = "Сперва задайте путь к директории";
    public static final String EXIT = "Конец работы";

    public static final String MENU = """
                1 - Указать кэшируемую директорию
                2 - Загрузить сожержимое файла в кэш
                3 - Просмотр кэш
                4 - Получить содержимое файла из кэш
                Введите любое другое число для выхода.
            """;

    public static void main(String[] args) throws IOException {
        start(new Scanner(System.in));
    }

    private static void start(Scanner sc) throws IOException {
        boolean run = true;
        String dir;
        DirFileCache dfCache = null;

        while (run) {
            System.out.println(MENU);
            System.out.println(MENU_SELECT);
            int userChoice = Integer.parseInt(sc.nextLine());
            System.out.println(userChoice);
            if (CACHING_DIR == userChoice) {
                System.out.println(MENU_LOAD_DIR);
                dir = sc.nextLine();
                validate(dir);
                dfCache = new DirFileCache(dir);
            } else if (dfCache != null && LOAD == userChoice) {
                dfCache.loadAllFiles();
                System.out.println(MENU_LOAD_DONE);
            } else if (dfCache != null && CACHE_PREVIEW == userChoice) {
                dfCache.printCache();
            } else if (dfCache != null && GET == userChoice) {
                System.out.println(MENU_LOAD_FILE);
                String fileName = sc.nextLine();
                System.out.println(dfCache.get(fileName));
            } else if (dfCache == null
                    && LOAD == userChoice
                    || CACHE_PREVIEW == userChoice
                    || GET == userChoice) {
                System.out.println(MENU_DIR_ERROR);
            } else {
                run = false;
                System.out.println(EXIT);
            }
        }
    }

    public static void validate(String dir) {
        Path path = Paths.get(dir);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Root doesn't exist");
        }
        if (!path.toFile().isDirectory()) {
            throw new IllegalArgumentException("Searching root is file");
        }
    }
}
