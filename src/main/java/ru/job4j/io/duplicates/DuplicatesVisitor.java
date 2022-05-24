package ru.job4j.io.duplicates;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Map<FileProperty, List<String>> fp = new HashMap<>();

    public List<List<String>> getDuplicates() {
        List<List<String>> rsl = new ArrayList<>();
        for (FileProperty ex : fp.keySet()) {
            List<String> list = fp.get(ex);
            if (list.size() > 1) {
                rsl.add(list);
            }
        }
        return rsl;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty ex = new FileProperty(file.toFile().length(), file.getFileName().toString());

        fp.putIfAbsent(ex, new ArrayList<>());
        fp.computeIfPresent(ex, (k, v) -> {
                                            v.add(file.toString());
                                            return v;
                                            });
        return super.visitFile(file, attrs);
    }
}