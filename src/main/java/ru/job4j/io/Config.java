package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

public class Config {

    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            Pattern accept = Pattern.compile("(^#.*$)|(^\\s*$)");
            for (String s : read.lines().toList()) {
                if (!accept.matcher(s).find()) {
                    String[] str = s.split("=", 2);
                    if (str.length == 2 && !str[0].equals("") && !str[1].equals("")) {
                        values.put(str[0], str[1]);
                    } else {
                        throw new IllegalArgumentException("Template error");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

}
