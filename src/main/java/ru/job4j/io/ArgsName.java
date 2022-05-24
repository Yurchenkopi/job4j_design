package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Collection doesn't contain current key.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        for (String s : args) {
            String[] str = s.split("=", 2);
            if (str[0].charAt(0) == '-') {
                StringBuilder sb = new StringBuilder(str[0]);
                sb.delete(0, 1);
                str[0] = sb.toString();
            }
            if (str.length == 1 || str[0].isEmpty() || str[1].isEmpty()) {
                throw new IllegalArgumentException("Template error");
            }
            values.put(str[0], str[1]);
        }
    }

    public static ArgsName of(String[] args) {
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}