package ru.job4j.cache;

import ru.job4j.io.Search;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }
    public void loadAllFiles() throws IOException {
        Path start = Paths.get(cachingDir);
        List<Path> listOfFiles =
               Search.search(start, p -> p.toString().endsWith("txt"));
        if (listOfFiles.size() == 0) {
            throw new NullPointerException("Directory doesnt contain files");
        }
        for (Path file : listOfFiles) {
            put(file.getFileName().toString(), load(file.toString()));
        }
    }
    @Override
    protected String load(String key) {
        String rsl = "";
        try (BufferedReader in = new BufferedReader(new FileReader(key))) {
            rsl = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}