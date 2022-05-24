package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<File> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (File f : sources) {
                zip.putNextEntry(new ZipEntry(f.getPath()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(f))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validate(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Source/Target/Searching parameter are null.");
        }
        ArgsName argsName = ArgsName.of(args);
        String source = argsName.get("d");
        String filter = argsName.get("e");
        Path path = Paths.get(source);
        if (!path.toFile().exists()) {
            throw new IllegalArgumentException("Source root doesn't exist");
        }
        if (!filter.startsWith(".")) {
            throw new IllegalArgumentException("Incorrect searching parameter. It should be starts with '.' ");
        }

    }

    public static void main(String[] args) throws IOException {
        validate(args);
        ArgsName argsName = ArgsName.of(args);
        String source = argsName.get("d");
        String filter = argsName.get("e");
        String target = argsName.get("o");
        Zip zip = new Zip();
        List<File> list = Search.search(Paths.get(source), p -> !p.toString().endsWith(filter)).stream()
                .map(Path::toFile)
                .toList();
        List<File> testList = List.of(
                        new File("./pom.xml"),
                        new File("./result.txt"),
                        new File("./server.log")
        );
        zip.packFiles(list, new File(target));
    }
}
