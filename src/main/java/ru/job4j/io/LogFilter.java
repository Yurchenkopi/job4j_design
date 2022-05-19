package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> data = new ArrayList<>();
        Pattern pattern = Pattern.compile(".*\\s" + "404" + "\\s.[^\\s]*$");
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            data = in.lines()
                    .filter(s -> pattern.matcher(s).find())
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)
                ))) {
            for (String s : log) {
                out.printf("%s%n", s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        log.forEach(System.out :: println);
        save(log, "404.txt");
    }
}