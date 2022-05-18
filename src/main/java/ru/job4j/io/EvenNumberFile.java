package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream in = new FileInputStream("even.txt")) {
            StringBuilder text = new StringBuilder();
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
            String[] lines = text.toString().split(System.lineSeparator());
            Arrays.stream(lines)
                    .forEach(s -> System.out.println(s + " : "
                            + ((Integer.parseInt(s) % 2 == 0) ? "even" : "odd")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
