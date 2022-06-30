package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CSVReader  {

    public static void handle(ArgsName argsName) throws IOException {
        validate(argsName);
        List<List<String>> data = new ArrayList<>();
        List<List<String>> filteredList = new ArrayList<>();
        String source = argsName.get("path");
        String target = argsName.get("out");
        String delimiter = argsName.get("delimiter");
        String[] filters = argsName.get("filter").split(",");
        int index = 0;
        int size = 0;
        try (Scanner scanner = new Scanner(Paths.get(source).toFile());
             PrintWriter out = "stdout".equals(target)
                     ? new PrintWriter(System.out)
                     : new PrintWriter(new FileWriter(target))) {
            String[] head = scanner.nextLine().split(delimiter);
            size = head.length;
            for (String s : head) {
                List<String> list = new ArrayList<>();
                list.add(s);
                data.add(list);
            }
            scanner.useDelimiter("(;)|(\n)|(\r\n)");
            while (scanner.hasNext()) {
                String value = scanner.next();
                data.get(index).add(value);
                index++;
                if (index == size) {
                    index = 0;
                }
            }
            for (String f : filters) {
                for (List<String> list : data) {
                    if (f.equals(list.get(0))) {
                        filteredList.add(list);
                        break;
                    }
                }
            }
            for (int i = 0; i < data.get(0).size(); i++) {
                StringJoiner rsl = new StringJoiner(delimiter);
                for (int j = 0; j < filters.length; j++) {
                    rsl.add(filteredList.get(j).get(i));
                }
                out.println(rsl);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void validate(ArgsName args) {
        Path source = Paths.get(args.get("path"));
        if (!source.toFile().exists()) {
            throw new IllegalArgumentException("Root doesn't exist");
        }
        if (source.toFile().isDirectory()) {
            throw new IllegalArgumentException("File is root");
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName par = ArgsName.of(new String[]{"-path=C:\\projects\\job4j_design\\data\\CSVReader\\CSVtest.csv",
                "-delimiter=;", "-out=stdout", "-filter=Num,Model,Speed"});
        handle(par);
    }
}