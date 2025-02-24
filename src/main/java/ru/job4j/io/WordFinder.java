package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;

public class WordFinder {
    public static void main(String[] args) {
        var sb = new StringBuilder();
        var pattern = Pattern.compile(args[0]);
        int count = 0;
        Path p = Paths.get(".\\data\\input\\EvgeniyOnegin.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(p.toFile(), Charset.forName("WINDOWS-1251")))) {
            br.lines().map(s -> s + System.lineSeparator()).forEach(sb::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : sb.toString().split("(\n)|(\r\n)|( )")) {
            var matcher = pattern.matcher(s);
            if (matcher.find()) {
                count++;
            }
        }
        System.out.printf("Nums of \"%s\": %d%s", args[0], count, System.lineSeparator());
    }
}
