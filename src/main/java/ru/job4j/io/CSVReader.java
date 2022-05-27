package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.sql.Struct;
import java.util.*;

public class CSVReader {
    public static void handle(ArgsName argsName) throws Exception {
        Map<String, List<String>> data = new HashMap<>();
        String delimiter = argsName.get("delimiter").substring(1, 2);
        String[] filters = argsName.get("filter").split(",");
        boolean firstLoop = true;
        try (Scanner scanner = new Scanner(Paths.get(argsName.get("path")).toFile()).useDelimiter(delimiter)) {
            while (firstLoop && scanner.hasNext()) {
                String key = scanner.next();
                if (!System.lineSeparator().equals(key) && firstLoop) {
                    data.put(key, new ArrayList<>());
                } else {
                    firstLoop = false;
                }
            }
            while (scanner.hasNext()) {
                String value = scanner.next();
                data.computeIfPresent()
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("stdout".equals(argsName.get("out"))) {
            data.stream().forEach(System.out :: print);
        }
        System.out.println(data);
    }

    public static void main(String[] args) throws Exception {
        ArgsName par = ArgsName.of(new String[] {"-path=C:\\projects\\job4j_design\\data\\CSVReader\\CSVtest.csv", "-delimiter=\";\"", "-out=stdout", "-filter=color, model"});
        handle(par);
    }
}