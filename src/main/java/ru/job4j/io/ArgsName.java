package ru.job4j.io;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ArgsName {

    private final Map<String, String> values = new HashMap<>();

    private void validate(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Empty parameters");
        }
        for (String s : args) {
            Pattern pattern = Pattern.compile("^-.+=.+$");
            if (!pattern.matcher(s).find()) {
                throw new IllegalArgumentException("Template error");
            }
        }
    }

    public String get(String key) {
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException("Collection doesn't contain current key.");
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        validate(args);
        for (String s : args) {
            String[] str = s.split("=", 2);
            StringBuilder sb = new StringBuilder(str[0]);
            sb.delete(0, 1);
            str[0] = sb.toString();
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