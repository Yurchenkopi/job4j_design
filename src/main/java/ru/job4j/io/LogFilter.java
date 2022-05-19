package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> data = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
           data = in.lines()
                    .map(l -> l.split(" "))
                    .filter(f -> Integer.parseInt(f[f.length - 2]) == 404)
                    .map(s -> {
                        StringBuilder b = new StringBuilder();
                        for (String str : s) {
                            b.append(str);
                            b.append(' ');
                        }
                        return b + System.lineSeparator();
                    })
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("log.txt");
        System.out.println(log);

    }
}